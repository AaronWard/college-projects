# Thesis Template

Report will be written using Pandoc, each section segregated into its own file/folder.
 Just start getting the ideas flowing, it doesn't have to be perfect
 first time. Write, commit, push and review it together weekly.   

Requirements : Install Pandoc, Latex and NPM on your machine
(NPM not essential but highly recommended to automate builds) 

 - Refer to package.json to see scripts for building on windows or mac,  
 example: for mac - npm run pdfmac  
          for windows - npm run pdfwin 

We've written a Build.java that executes the build command for windows, as windows doesn't have strong wildcard command support like macOS does. Code isn't great, but it works. It will only work on the condition that no more subdirectories are added! So don't add anymore subdirectories unless you want to update the Build.java program.

Recommended: run a clean command before building again, overwriting files constantly can lead to  
build-fails  

example - for mac - npm run pdfmac THEN BEFORE BUILDING AGAIN npm run cleanmac  
Same process for windows - see package.json scripts to see whats going on

Uploaded to this repository will be:
 - All docs relating to deliverables on the report will be in './resource/' for reference/guidelines/deadlines and everything the team needs to know
 - The report itself, segregated by chapter/section, easy to navigate to the section you wish to update   
 - All necessary configuration files to build the PDF i.e package.json configs

 For all other queries regarding inserting images, table of contents, bibliographies, references and other configuration and setup stuff   
 Go here:     
 !! READ THE README FIRST !! ----> https://github.com/dr-matt-smith/pandoc-thesis-template
