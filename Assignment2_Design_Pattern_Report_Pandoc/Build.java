import java.io.*;
import java.util.Arrays;

public class Build {

    public static void main(String[] args) throws IOException,Exception {

        File frontMaterial = new File(System.getProperty("user.dir")+"/00_front-material");
        File introduction = new File(System.getProperty("user.dir")+"/01_sections/00_introduction");
        File system_reqs = new File(System.getProperty("user.dir")+"/01_sections/03_system-requirements_specification");
        File sys_design = new File(System.getProperty("user.dir")+"/01_sections/04_system_design");
        File implementation = new File(System.getProperty("user.dir")+"/01_sections/05_implementation");
        File conclusion = new File(System.getProperty("user.dir")+"/01_sections/07_conclusions_furtherwork");
        File references = new File(System.getProperty("user.dir")+"/03_references");

        FilenameFilter fileFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".md");
            }
        };

        File[] frontMaterialFiles = frontMaterial.listFiles(fileFilter);
        File[] introFiles = introduction.listFiles(fileFilter);
        File[] sys_reqs_files = system_reqs.listFiles(fileFilter);
        File[] sys_design_files = sys_design.listFiles(fileFilter);
        File[] implementation_files = implementation.listFiles(fileFilter);
        File[] conclusion_files = conclusion.listFiles(fileFilter);
        File[] referencesFiles = references.listFiles(fileFilter);

        String[] frontMaterialFileNames = new String[frontMaterialFiles.length];
        String[] introFileNames = new String[introFiles.length];
        String[] sys_req_filenames = new String[sys_reqs_files.length];
        String[] sys_design_filenames = new String[sys_design_files.length];
        String[] implementation_filenames = new String[implementation_files.length];
        String[] conclusion_filenames = new String[conclusion_files.length];
        String[] referncesFileNames = new String[referencesFiles.length];

		int i=0;
		for (File file : frontMaterialFiles) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			}
			else if(file.isFile()) {
				frontMaterialFileNames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : introFiles) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				introFileNames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : sys_reqs_files) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				sys_req_filenames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : sys_design_files) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				sys_design_filenames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : implementation_files) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				implementation_filenames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : conclusion_files) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				conclusion_filenames[i] = file.getCanonicalPath();
				i++;
			}
		}

		i=0;
		for (File file : referencesFiles) {
			if (file.isDirectory()) {
				System.out.print("Don't create anymore directories in here!!!!");
			} else {
				referncesFileNames[i] = file.getCanonicalPath();
				i++;
			}
		}

		String frontmatpaths = "";
		for(int t = 0; t < frontMaterialFileNames.length; t++){
			frontmatpaths += frontMaterialFileNames[t]+" ";
		}

		String intropaths = "";
		for(int t = 0; t < introFileNames.length; t++){
			intropaths += introFileNames[t]+" "; 
		}

		String sysreqpaths = "";
		for(int t = 0; t <  sys_req_filenames.length; t++){
			sysreqpaths +=  sys_req_filenames[t]+" "; 
		}

		String sysdesignpaths = "";
		for(int t = 0; t < sys_design_filenames.length; t++){
			sysdesignpaths += sys_design_filenames[t]+" ";
		}

		String imppaths = "";
		for(int t = 0; t < implementation_filenames.length; t++){
			imppaths += implementation_filenames[t]+" "; 
		}
		String conclusionpaths = "";
		for(int t = 0; t < conclusion_filenames.length; t++){
			conclusionpaths += conclusion_filenames[t]+" "; 
		}

        ProcessBuilder builder = new ProcessBuilder(
				"cmd.exe", "/c"+"pandoc --latex-engine=xelatex -H "+
				"_config/preamble.tex -V fontsize=10pt -V documentclass:book"+
				" -V papersize:a4paper -V classoption:openright --number-sections"+
				" --bibliography=03_references/references.bib "+
				frontmatpaths+
				"_config/toc.md "+
				intropaths+
				sysreqpaths+
				sysdesignpaths+
				imppaths+
				conclusionpaths+
				"03_references/references.md"+
				" -o output.pdf"
            );

		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
		}
		System.out.println(line);
        }
     }
}