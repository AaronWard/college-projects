import java.util.*;
import processing.core.*;
import com.fuzzylite.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;

@SuppressWarnings("serial")
public class FuzzyPumpController extends PApplet{

    // Max and Min tank levels
    private final int MAX = 70;
    private final int MIN = 40;

    // For check buttons
    private boolean pumpOver = false;
    private boolean rainOver = false;
    private boolean demandOver = false;

    // Toggle external disturbances
    private boolean pumpOn = false;
    private boolean rainOn = false;
    private boolean demandOn = false;

    // Step vars to control perlin noise
    private float t1 = (float) 3;
    private float t2 = (float) 3;

    // Currrent level in the tank
    private float level;
    // Level of demand from outlet (-/+)
    private float demand;
    // Level of rain water filing tank
    private float rain;
    // Pump action (-/+)
    private float pumpAction;

    // Setup some colours
    private int blue = color(85,145,232);
    private int white = color(255,255,255);
    private int red = color(255,0,0);
    private int green = color(0,204,102);
    private int black = color(0,0,0);
    private int grey = color(100,100,100);

    // Fuzzy Logic objects
    private Engine engine;
    private InputVariable inputVariable1;
    private InputVariable inputVariable2;
    private OutputVariable outputVariable;
    private RuleBlock ruleBlock;

    public void settings(){
        size(800, 360);
    }

    public void setup(){
        Random ran = new Random();
        level = ran.nextInt(80)+10;
        demand = 0;
        pumpAction = 0;
        rain = 0;

        // TODO: Create the engine
        engine = new Engine();
        engine.setName("engine");

        // TODO: Setup the level input variable
        inputVariable1  = new InputVariable();
        inputVariable1.setEnabled(true);
    	inputVariable1.setName("level");
    	inputVariable1.setRange(0.00, 100.00);
		inputVariable1.addTerm(new Trapezoid("very_low", 65.000, 70.000, 100.000, 100.000)); /** change these */
		inputVariable1.addTerm(new Triangle("low", 35.000, 45.000, 55.000));
		inputVariable1.addTerm(new Triangle("average", 45.000, 55.000, 65.000));
		inputVariable1.addTerm(new Triangle("high", 55.000, 65.000, 75.000));
		inputVariable1.addTerm(new Trapezoid("very_high", 65.000, 70.000, 100.000, 100.000));
		engine.addInputVariable(inputVariable1);
    	
      	
        // TODO: Setup the demand input variable
        inputVariable2 = new InputVariable();
        inputVariable2.setEnabled(true);
    	inputVariable2.setName("demand");
    	inputVariable1.setRange(-1.00, 1.50); 
		inputVariable2.addTerm(new Trapezoid("very_low", -1.000, -1.000, -0.750, -0.250));  /** change these */
		inputVariable2.addTerm(new Triangle("low", -0.750, -0.250, 0.250));
		inputVariable2.addTerm(new Triangle("none", -0.250, 0.250, 0.750));
	    inputVariable2.addTerm(new Triangle("high", 0.250, 0.750, 1.250));
		inputVariable2.addTerm(new Trapezoid("very_high", 0.750, 1.250, 1.500, 1.500));
    	engine.addInputVariable(inputVariable2);
        
        
        // TODO: Setup the output command variable
	    outputVariable = new OutputVariable();
	  	outputVariable.setEnabled(true);
		outputVariable.setName("command");
		outputVariable.setRange(-1.000, 1.000);
		outputVariable.fuzzyOutput().setAccumulation(new Maximum());
		outputVariable.setDefuzzifier(new Centroid(200));
		outputVariable.setDefaultValue(0.000);
		outputVariable.setLockValidOutput(false);
		outputVariable.setLockOutputRange(false);
		outputVariable.addTerm(new Trapezoid("pump_in", -1.000, -1.000, -0.500, -0.250)); /** Possible triangles instead/ test it out */
		outputVariable.addTerm(new Trapezoid("no_pump", -0.500, -0.250, 0.250, 0.500));
		outputVariable.addTerm(new Trapezoid("pump_out", 0.250, 0.500, 1.000, 1.000));
		engine.addOutputVariable(outputVariable);
        
        // TODO: Setup the inference rules
        ruleBlock = new RuleBlock();
    	ruleBlock.setName("rule");
    	ruleBlock.setEnabled(true);
    	ruleBlock.setConjunction(new Minimum());
        ruleBlock.setDisjunction(new Maximum());
        ruleBlock.setActivation(new Minimum());

//		ruleBlock.addRule(Rule.parse("if (level is very_low or level is low) and (demand is very_high) or (demand is high) then command is pump_in", engine));
//		ruleBlock.addRule(Rule.parse("if (level is very_high or level is high) and (demand is very_high) or (demand is high) then command is pump_out", engine));
//		ruleBlock.addRule(Rule.parse("if (level is very_low or level is low or level is average) and demand is none then command is no_pump", engine));
		
//		
//		ruleBlock.addRule(Rule.parse("if (level is very_low or level is low) then command is no_pump", engine));
//		ruleBlock.addRule(Rule.parse("if (level is average) and demand is low or demand is very_low then command is no_pump", engine));


		ruleBlock.addRule(Rule.parse("if (level is very_low or level is low) then command is pump_out", engine)); //THIS WORKS
		ruleBlock.addRule(Rule.parse("if (level is very_high or level is high) then command is pump_in", engine)); //THIS WORKS
		ruleBlock.addRule(Rule.parse("if (level is average) then command is no_pump", engine)); //THIS WORKS
		ruleBlock.addRule(Rule.parse("if demand is very_high and level is very_low then command is no_pump", engine)); //THIS WORKS


		
		
		engine.addRuleBlock(ruleBlock);		
        // Set the background to black
        background(255);
    }
    private void drawGameOver(){
        background(red);
        fill(white);
        textSize(60);
        text("CRITICAL LEVEL",200,100);
    }
    private void drawPipes(){
        // Set the stroke color
        stroke(0);
        // Draw the inlet pipe
        line(100,290,500,290);
        line(100,300,500,300);
        // Draw the outlet pipe
        line(600,290,750,290);
        line(600,300,750,300);
        noStroke();
        // Fill the input pipe
        fill(blue);
        rect(100,291,400,9);
        // Fill the output pipe
        rect(601,291,150,9);
    }
    private void drawPump(){
        // Draw the pump
        fill(grey);
        rect(255,275,40,40);
    }
    private void drawTank(){
        // Draw the tank
        stroke(0);
        line(500,300,500,200);
        line(600,300,600,200);
        line(500,300,600,300);
    }
    private void drawWaterLevel(float lev){
        // No outline for the water
        noStroke();
        // Set the fill color
        fill(blue);
        // Draw the rect for the water
        rect(501,300-lev,99,lev);
    }
    private void drawInfo(float l, float d, float p){
        // Output the water level
        fill(black);
        text("Water level: " + l,50,50);
        text("Demand level: " + d,50,65);
        text("Pump: " + p,50,80);
        if (l > MAX || l < MIN)
            fill(red);
        else
            fill(green);
        // Draw the warning light
        ellipse(60,95,20,20);
        fill(white);
        if (p < 0)
            text("<<<", 261,298);
        else
            text(">>>", 261,298);
    }
    private float fuzzyPumpController(float l, float d){
        // TODO: Load the input variables
    	inputVariable1.setInputValue(l);
    	
    	inputVariable2.setInputValue(d);
    	// TODO: Run the engine
    	engine.process();

		// TODO: Return the output -> outputVariable.defuzzify()
		return (float) outputVariable.defuzzify();
    }
    private void drawCheckboxButtons(){
        // Draw the buttons
        stroke(black);
        if (pumpOn)
            fill(grey);
        else
            fill(white);
        rect(650, 30, 15, 15);
        fill(black);
        text("Pump", 670, 42);
        stroke(black);
        if (rainOn)
            fill(grey);
        else
            fill(white);
        rect(650, 50, 15, 15);
        fill(black);
        text("Rain", 670, 62);
        stroke(black);
        if (demandOn)
            fill(grey);
        else
            fill(white);
        rect(650, 70, 15, 15);
        fill(black);
        text("Demand", 670, 83);


    }

    // Run the system
    public void drawSystem(){
        // Clear the background
        background(255);

        // Update the mouse pos
        update(mouseX, mouseY);

        drawCheckboxButtons();

        // Draw all the static visual components
        drawPipes();
        drawPump();
        drawTank();

        // Generate a perlin level of rain
        if (rainOn)
            rain = noise(t1) * 0.05f;
        else
            rain = 0;
        // Generate a perlin outlet demand
        if (demandOn){
            demand = noise(t2);
            // Map the demand to a value between -1 and 1.5
            demand = map(demand,0f,1f,-1f,1.5f);
        }
        else
            demand = 0;

        // Add the rain level to the current tank level
        level = level + rain;
        // Apply the demand -/+ to the current level
        level = level - demand;

        // Show the water level in the tank
        drawWaterLevel(level);

        // Run the fuzzy engine with inputs and get controller output
        if (pumpOn == true){
            pumpAction = fuzzyPumpController(level, demand);
            // Apply the pump action to the current level
            level = level + pumpAction;
        }

        // Draw the instrumentation panel
        drawInfo(level, demand, pumpAction);

        // Increment time step for Perlin noise
        t1 += 0.01;
        t2 += .6;
    }

    // Draw each frame of animation
    public void draw(){
        if (level < 0 || level > 100)
            drawGameOver();
        else
            drawSystem();
    }
    private void update(int x, int y) {
        if (overCheckbox(650, 30, 15, 15) ) {
            pumpOver = true;
            rainOver = false;
            demandOver = false;
        }
        else if (overCheckbox(650, 50, 15, 15) ) {
            rainOver = true;
            pumpOver = false;
            demandOver = false;
        }
        else if (overCheckbox(650, 70, 15, 15) ) {
            demandOver = true;
            pumpOver = false;
            rainOver = false;
        }
        else {
            pumpOver = rainOver = demandOver = false;
        }
    }
    public void mousePressed() {
        if (rainOver) {
            rainOn = !rainOn;
        }
        if (demandOver) {
            demandOn = !demandOn;
        }
        if (pumpOver){
            pumpOn = !pumpOn;
        }
    }
    private boolean overCheckbox(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height)
            return true;
        else
            return false;
    }

    // Main method
    public static void main(String[] args) {
        PApplet.main("FuzzyPumpController");
    }
}