@echo off
javac -classpath ".\melissadata\businesssearch\org.apache.sling.commons.json-2.0.20.jar;" .\melissadata\businesssearch\*.java .\melissadata\businesssearch\view\*.java ./melissadata\businesssearch\model\*.java
java -classpath ".\melissadata\businesssearch\org.apache.sling.commons.json-2.0.20.jar;"; melissadata.businesssearch.Main melissadata.businesssearch.view.BusinessSearchController melissadata.businesssearch.view.BusinessSearchTransactionController melissadata.businesssearch.view.RootLayoutController melissadata.businesssearch.model.BusinessSearchOption
del .\melissadata\businesssearch\*.class 
del .\melissadata\businesssearch\view\*.class 
del .\melissadata\businesssearch\model\*.class