// User extensions can be added here.
//
// Keep this file to avoid  mystifying "Invalid Character" error in IE

/**
 * storeValue, storeText, storeAttribute and store actions now 
 * have 'global' equivalents.
 * Use storeValueGlobal, storeTextGlobal, storeAttributeGlobal or storeGlobal
 * will store the variable globally, making it available it subsequent tests.
 *
 * See the Reference.html for storeValue, storeText, storeAttribute and store
 * for the arguments you should send to the new Global functions.
 *
 * example of use
 * in testA.html:
 * +------------------+----------------------+----------------------+
 * |storeGlobal       | http://localhost/    | baseURL              |
 * +------------------+----------------------+----------------------+
 * 
 * in textB.html (executed after testA.html):
 * +------------------+-----------------------+--+
 * |open              | ${baseURL}Main.jsp    |  |
 * +------------------+-----------------------+--+
 *
 * Note: Selenium.prototype.replaceVariables from selenium-api.js has been replaced
 *       here to make it use global variables if no local variable is found.
 *       This might cause issues if you upgraded Selenium in the future and this function 
 *       has been changed.
 *
 * @author Guillaume Boudreau
 */
 
globalStoredVars = new Object();

/*
 * Globally store the value of a form input in a variable
 */
Selenium.prototype.doStoreValueGlobal = function(target, varName) {
    if (!varName) {
        // Backward compatibility mode: read the ENTIRE text of the page
        // and stores it in a variable with the name of the target
        value = this.page().bodyText();
        globalStoredVars[target] = value;
        return;
    }
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getInputValue(element);
};

/*
 * Globally store the text of an element in a variable
 */
Selenium.prototype.doStoreTextGlobal = function(target, varName) {
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getText(element);
};

/*
 * Globally store the value of an element attribute in a variable
 */
Selenium.prototype.doStoreAttributeGlobal = function(target, varName) {
    globalStoredVars[varName] = this.page().findAttribute(target);
};

/*
 * Globally store the result of a literal value
 */
Selenium.prototype.doStoreGlobal = function(value, varName) {
    globalStoredVars[varName] = value;
};

/*
 * Search through str and replace all variable references ${varName} with their
 * value in storedVars (or globalStoredVars).
 */
Selenium.prototype.replaceVariables = function(str) {
    var stringResult = str;

    // Find all of the matching variable references
    var match = stringResult.match(/\$\{\w+\}/g);
    if (!match) {
        return stringResult;
    }

    // For each match, lookup the variable value, and replace if found
    for (var i = 0; match && i < match.length; i++) {
        var variable = match[i]; // The replacement variable, with ${}
        var name = variable.substring(2, variable.length - 1); // The replacement variable without ${}
        var replacement = storedVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
        var replacement = globalStoredVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
    }
    return stringResult;
};

Selenium.prototype.doEncodePW = function( str_in, varName )
{
	if (str_in == null || str_in == "") {
  	throw new SeleniumError("You must specify an input string (Target)");
  }
	if (varName == null || varName == "") {
  	throw new SeleniumError("You must specify a variable name to store encoded password in (Value)");
  }
	num_out = "";
	str_in = escape(str_in);
	for(i = 0; i < str_in.length; i++) 
	{
		num_out += str_in.charCodeAt(i) - 23;
	}
	storedVars[varName] = num_out;
}

Selenium.prototype.doDecodePW = function( str_in, varName )
{
	if (str_in == null || str_in == "") {
  	throw new SeleniumError("You must specify an input string (Target)");
  }
	if (varName == null || varName == "") {
  	throw new SeleniumError("You must specify a variable name to store decoded password in (Value)");
  }
  str_out = "";
  for(i = 0; i < str_in.length; i += 2) 
  {
		num_in = parseInt(str_in.substr(i,[2])) + 23;
		num_in = unescape('%' + num_in.toString(16));
	str_out += num_in;
	}
	storedVars[varName] = str_out;
}


/*
 (C) Copyright MetaCommunications, Inc. 2006.
     http://www.meta-comm.com
     http://engineering.meta-comm.com

Distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND.
*/

function map_list( list, for_func, if_func )
    {
    var mapped_list = [];
    for ( var i = 0; i < list.length; ++i )
        {
        var x = list[i];
        if( null == if_func || if_func( i, x ) ) 
            mapped_list.push( for_func( i, x ) );
        }
    return mapped_list;
    }

    
// Modified to initialize GoTo labels/cycles list
HtmlRunnerTestLoop.prototype.old_initialize = HtmlRunnerTestLoop.prototype.initialize

HtmlRunnerTestLoop.prototype.initialize = function(htmlTestCase, metrics, seleniumCommandFactory)
    {
    this.gotoLabels  = {};
    this.whileLabels = { ends: {}, whiles: {} };
    
    this.old_initialize(htmlTestCase, metrics, seleniumCommandFactory);
    
    this.initialiseLabels();
    }

HtmlRunnerTestLoop.prototype.initialiseLabels = function()
    {
    var command_rows = map_list( this.htmlTestCase.getCommandRows() 
                               , function(i, x) { 
                                    return x.getCommand()
                                    }
                               );

    var cycles = [];
    for( var i = 0; i < command_rows.length; ++i )
        {
        switch( command_rows[i].command.toLowerCase() )
            {
            case "label":
                this.gotoLabels[ command_rows[i].target ] = i;
                break;
            case "while":
            case "endwhile":
                cycles.push( [command_rows[i].command.toLowerCase(), i] )
                break;
            }
        }        
        
    var i = 0;
    while( cycles.length )
        {
        if( i >= cycles.length )
            throw new Error( "non-matching while/endWhile found" );
            
        switch( cycles[i][0] )
            {
            case "while":
                if(    ( i+1 < cycles.length ) 
                    && ( "endwhile" == cycles[i+1][0] )
                    )
                    {
                    // pair found
                    this.whileLabels.ends[ cycles[i+1][1] ] = cycles[i][1]
                    this.whileLabels.whiles[ cycles[i][1] ] = cycles[i+1][1]
                    
                    cycles.splice( i, 2 );
                    i = 0;
                    }
                else
                    ++i;
                break;
            case "endwhile":
                ++i;
                break;
            }
        }
                    
    }    

HtmlRunnerTestLoop.prototype.continueFromRow = function( row_num ) 
    {
    if(    row_num == undefined
        || row_num == null
        || row_num < 0
        )
        throw new Error( "Invalid row_num specified." );
        
    this.htmlTestCase.nextCommandRowIndex = row_num;
    }
    


// do nothing. simple label
Selenium.prototype.doLabel      = function(){};

Selenium.prototype.doGotolabel  = function( label ) {

    if( undefined == htmlTestRunner.currentTest.gotoLabels[label] ) 
        throw new Error( "Specified label '" + label + "' is not found." );
    
    htmlTestRunner.currentTest.continueFromRow( htmlTestRunner.currentTest.gotoLabels[ label ] );
    };
    
Selenium.prototype.doGoto = Selenium.prototype.doGotolabel;


Selenium.prototype.doGotoIf = function( condition, label ) {
    if( eval(condition) ) 
        this.doGotolabel( label );
    }


    
Selenium.prototype.doWhile = function( condition ) {
    if( !eval(condition) )
        {
        var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1
        var end_while_row = htmlTestRunner.currentTest.whileLabels.whiles[ last_row ]
        if( undefined == end_while_row ) 
            throw new Error( "Corresponding 'endWhile' is not found." );
        
        htmlTestRunner.currentTest.continueFromRow( end_while_row + 1 );
        }
    }


Selenium.prototype.doEndWhile = function() {
    var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1
    var while_row = htmlTestRunner.currentTest.whileLabels.ends[ last_row ]
    if( undefined == while_row ) 
        throw new Error( "Corresponding 'While' is not found." );
    
    htmlTestRunner.currentTest.continueFromRow( while_row );
    }
   

/* Start: Neuer Workflow Getit Selenium Userextension */

/*
* VAR's
*/
////////////////////////////
var GE_QUEUEENTRYEXISTS      = "GE_QUEUEENTRYEXISTS";
var GE_TCPARAMETERS          = "GE_TCPARAMETERS";
var GE_TCSTATUS              = "GE_TCSTATUS";
var GE_TCSTARTTIME           = "GE_TCSTARTTIME";
var GE_TSSTARTTIME           = "GE_TSSTARTTIME";
var GE_TCDURATIONTIME        = "GE_TCDURATIONTIME";
var GE_TSDURATIONTIME        = "GE_TSDURATIONTIME";
var GE_TCDESCRIPTION         = "GE_TCDESCRIPTION";
var GE_TCNO                  = "GE_TCNO";
var GE_TCID                  = "GE_TCID";
var GE_TCNAME                = "GE_TCNAME";
var GE_TSID                  = "GE_TSID";
var GE_TSNAME                = "GE_TSNAME";
var GE_TCLIMITATION          = "GE_TCLIMITATION";
var GE_TCPRIO                = "GE_TCPRIO";
var GE_TCNAMECONFIG          = "GE_TCNAMECONFIG";
var GE_LASTERROR             = "GE_LASTERROR";
var GE_OUTPUTHTMLONERROR     = "GE_OUTPUTHTMLONERROR";
var GE_HEADERCOVERAGE        = 1023;
var GE_LOGHEADERGENERATE     = false;
var GE_CountFailedTestsInTS  = 0;
var GE_CountOpenTestsInTS    = 0;
var GE_CountPassedTestsInTS  = 0;
////////////////////////////

/*
* Init's
*/
////////////////////////////
Selenium.prototype.doGe_tearUpTC = function(TCID,TCNAME)
{
LOG.debug("> ge_tearUpTC|" + TCID + "|" + TCNAME);

if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true){
this.doGe_setTCStatus("failed");
this._ge_logTCResult();
GE_CountFailedTestsInTS++;
}

this._ge_initializeTCStartTime();
this.doGe_setTCStatus("running");
this._ge_initializeTCParameters();
this._ge_setTCID(TCID);
this._ge_setTCName(TCNAME);
this._ge_setQueueEntryExists(true);
this._ge_initializeLastError();
this.doGe_setTCParameter("", GE_TCLIMITATION);
this.doGe_setTCParameter("", GE_TCPRIO);

LOG.debug("< doGe_tearUpTC|");
}

Selenium.prototype.doGe_tearDownTC = function()
{
LOG.debug("> ge_tearDownTC||");
if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true){
this.doGe_logTCWasSuccessful();
}
LOG.debug("< ge_tearDownTC|");
}

Selenium.prototype.doGe_tearUpTS = function(TSID,TSNAME)
{
LOG.debug("> doGe_tearUpTS|" + TSID + "|" + TSNAME);

if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true){
this.doGe_setTCStatus("failed");
this._ge_logTCResult();
GE_CountFailedTestsInTS++;
this.doGe_logTSResult();
GE_LOGHEADERGENERATE = false;
}
this._ge_initializeTCNo();
this._ge_initializeTSStartTime();
this._ge_setTSID(TSID);
this._ge_setTSName(TSNAME);
this._ge_generateLogHeader();
this._ge_initializeLastError();
this.doGe_setTCParameter("", GE_TCLIMITATION);

/*Init counting vars*/
GE_CountFailedTestsInTS  = 0;
GE_CountOpenTestsInTS    = 0;
GE_CountPassedTestsInTS  = 0;

LOG.debug("< doGe_tearUpTS|");
}

Selenium.prototype.doGe_tearDownTS = function()
{
LOG.debug("> doGe_tearDownTS||");

if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true){
this.doGe_setTCStatus("failed");
this._ge_logTCResult();
GE_CountFailedTestsInTS++;
}
this.doGe_logTSResult();
GE_LOGHEADERGENERATE = false;
LOG.debug("< doGe_tearDownTS|");
}

Selenium.prototype._ge_initializeTCStartTime = function()
{
LOG.debug("> _ge_initializeTCStartTime||");
this.doGe_setTCParameter(new Date().getTime(), GE_TCSTARTTIME);
LOG.debug("< _ge_initializeTCStartTime|");
}

Selenium.prototype._ge_initializeTSStartTime = function()
{
LOG.debug("> _ge_initializeTSStartTime||");
this.doGe_setTCParameter(new Date().getTime(), GE_TSSTARTTIME);
LOG.debug("< _ge_initializeTSStartTime|");
}

Selenium.prototype._ge_initializeTCStatus = function()
{
LOG.debug("> _ge_initializeTCStatus||");
this.doGe_setTCStatus("");
LOG.debug("< _ge_initializeTCStatus|");
}

Selenium.prototype._ge_initializeTCParameters = function()
{
LOG.debug("> _ge_initializeTCParameters||");
this.doGe_setTCParameter("", GE_TCPARAMETERS);
LOG.debug("< _ge_initializeTCParameters|");
}

Selenium.prototype._ge_initializeTCDescription = function()
{
LOG.debug("> _ge_initializeTCDescription||");
this.doGe_setTCParameter("", GE_TCDESCRIPTION);
LOG.debug("< _ge_initializeTCDescription|");
}

Selenium.prototype._ge_initializeTCNo = function()
{
LOG.debug("> _ge_initializeTCNo||");
this.doGe_setTCParameter(0, GE_TCNO);
LOG.debug("< _ge_initializeTCNo|");
}

Selenium.prototype._ge_initializeLastError = function()
{
LOG.debug("> _ge_initializeLastError||");
this.doGe_setTCParameter("", GE_LASTERROR);
LOG.debug("< _ge_initializeLastError|");
}
////////////////////////////

/*
* Setter
*/
////////////////////////////
Selenium.prototype.doGe_setTCParameter = function(Param, TCParameter)
{
LOG.debug("> ge_setTCParameter|'" + Param + "'|'" + TCParameter + "'");
globalStoredVars[TCParameter] = Param;
LOG.debug("< ge_setTCParameter|");
}

Selenium.prototype.doGe_setTCParameterEx1 = function(TCParameter, Param)
{
LOG.debug("> ge_setTCParameter|'" + Param + "'|'" + TCParameter + "'");
globalStoredVars[TCParameter] = Param;
LOG.debug("< ge_setTCParameter|");
}

Selenium.prototype._ge_setTCID  = function(TCID)
{
LOG.debug("> _ge_setTCID|" + TCID + "|");
this.doGe_setTCParameter(TCID, GE_TCID);
LOG.debug("< _ge_setTCID|");
}

Selenium.prototype._ge_setTCName = function(TCName)
{
LOG.debug("> _ge_setTCName|" + TCName + "|");
this.doGe_setTCParameter(TCName, GE_TCNAME);
LOG.debug("< _ge_setTCName|");
}

Selenium.prototype._ge_setTSID  = function(TSID)
{
LOG.debug("> _ge_setTSID|" + TSID + "|");
this.doGe_setTCParameter(TSID, GE_TSID);
LOG.debug("< _ge_setTSID|");
}

Selenium.prototype._ge_setTSName = function(TSName)
{
LOG.debug("> _ge_setTSName|" + TSName + "|");
this.doGe_setTCParameter(TSName, GE_TSNAME);
LOG.debug("< _ge_setTSName|");
}

Selenium.prototype._ge_setQueueEntryExists = function(state)
{
LOG.debug("> _ge_setQueueEntryExists|" + state + "|");
this.doGe_setTCParameter(state, GE_QUEUEENTRYEXISTS);
LOG.debug("< _ge_setQueueEntryExists|");
}

Selenium.prototype.doGe_setTCStatus = function(TCStatus)
{
LOG.debug("> ge_setTCStatus|"+ TCStatus.toLowerCase() + "|");
this.doGe_setTCParameter(TCStatus.toLowerCase(), GE_TCSTATUS);
LOG.debug("< ge_setTCStatus|");
}

Selenium.prototype.doGe_setTCDescription = function(Action, ExpectedResult)
{
LOG.debug("> ge_setTCDescription|" + Action + "|" + ExpectedResult);

this.doGe_setTCParameter(Action + ";" + ExpectedResult, GE_TCDESCRIPTION);
this._ge_initializeTCStartTime();
this.doGe_setTCStatus("running");
this._ge_setQueueEntryExists(true);

LOG.debug("< ge_setTCDescription|");
}

Selenium.prototype.doGe_setTCLimitation = function(Limitation) // deprecated
{
LOG.debug("> doGe_setTCLimitation|" + Limitation + "| !!!is deprecated - Please use ge_appendTCLimitation or ge_appendTCLimitation instead!!!");
LOG.getit("ge_setTCLimitation is deprecated. Please use ge_appendTCLimitation or ge_appendTCLimitation instead");
this.doGe_setTCParameter(Limitation, GE_TCLIMITATION);
LOG.debug("< doGe_setTCLimitation|");
}

Selenium.prototype.doGe_appendTCLimitationComment = function(Comment)
{
LOG.debug("> doGe_appendTCLimitationComment|" + Comment + "|");

if (this._ge_getParameter(GE_TCLIMITATION) != "")
this.doGe_setTCParameter( (this._ge_getParameter(GE_TCLIMITATION) + ", " + Comment), GE_TCLIMITATION);
else
this.doGe_setTCParameter( Comment, GE_TCLIMITATION);
LOG.debug("< doGe_appendTCLimitationComment|");
}

Selenium.prototype.doGe_appendTCLimitation = function(TCID, TCName)
{
LOG.debug("> doGe_appendTCLimitation|" + TCID + "|" + TCName);

if (this._ge_getParameter(GE_TCLIMITATION) != "")
this.doGe_setTCParameter( (this._ge_getParameter(GE_TCLIMITATION) + ", " + TCID + "-" + TCName), GE_TCLIMITATION);
else
this.doGe_setTCParameter( TCID + "-" + TCName, GE_TCLIMITATION);
LOG.debug("< doGe_appendTCLimitation|");
}

Selenium.prototype.doGe_setTCPrio = function(Prio)
{
LOG.debug("> doGe_setTCPrio|" + Prio + "|");
this.doGe_setTCParameter(Prio, GE_TCPRIO);
LOG.debug("< doGe_setTCPrio|");
}

Selenium.prototype.doGe_setTCNameConfig = function(Config)
{
LOG.debug("> doGe_setTCNameConfig|" + Config + "|");
this.doGe_setTCParameter(Config, GE_TCNAMECONFIG);
LOG.debug("< doGe_setTCNameConfig|");
}

Selenium.prototype.doGe_setLogHeaderCoverage = function(Coverage)
{
LOG.debug("> doGe_setLogHeaderCoverage|" + Coverage + "|");
if (! isNaN (Coverage-0) && Coverage != null)
{
GE_HEADERCOVERAGE = Coverage;
}
else
GE_HEADERCOVERAGE = 1023;
LOG.debug("< doGe_setLogHeaderCoverage|");
}

Selenium.prototype.doGe_enableHTMLOutputOnError = function()
{
LOG.debug("> doGe_enableHTMLOutputOnError||");
this.doGe_setTCParameter("true", GE_OUTPUTHTMLONERROR);
LOG.debug("< doGe_enableHTMLOutputOnError|");
}

Selenium.prototype.doGe_disableHTMLOutputOnError = function()
{
LOG.debug("> doGe_disableHTMLOutputOnError||");
this.doGe_setTCParameter("false", GE_OUTPUTHTMLONERROR);
LOG.debug("< doGe_disableHTMLOutputOnError|");
}

////////////////////////////

/*
* Log's
*/
////////////////////////////
Selenium.prototype.doGe_logTCWasSuccessful = function()
{
LOG.debug("> ge_logTCWasSuccessful||");

if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true && this._ge_getParameter(GE_TCSTATUS) == "running"){
this.doGe_setTCStatus("passed");
GE_CountPassedTestsInTS++;
this._ge_logTCResult();
this.doGe_setTCParameter("", GE_TCLIMITATION);
this.doGe_setTCParameter("", GE_TCPRIO);
}
if (this._ge_getParameter(GE_QUEUEENTRYEXISTS) == true && this._ge_getParameter(GE_TCSTATUS) == "open"){
GE_CountOpenTestsInTS++;
this._ge_logTCResult();
}
LOG.debug("< ge_logTCWasSuccessful|");
}

Selenium.prototype._ge_logTCResult = function()
{
LOG.debug("> ge_logTCResult||");
globalStoredVars[GE_TCNO]++;
this._ge_logTCRuntime();
this._ge_generateTCLogLine();
this._ge_setQueueEntryExists(false);
LOG.debug("< ge_logTCResult|");
}

Selenium.prototype.doGe_logTSResult = function()
{
LOG.debug("> ge_logTSResult||");
this._ge_logTSRuntime();
this._ge_generateTSLogLine();
this._ge_setQueueEntryExists(false);
LOG.debug("< ge_logTSResult|");
}

Selenium.prototype._ge_logTCRuntime = function()
{
LOG.debug("> _ge_logTCRuntime||");
var duration = new Date().getTime() - this._ge_getParameter(GE_TCSTARTTIME);
this.doGe_setTCParameter(duration, GE_TCDURATIONTIME);
LOG.debug("< _ge_logTCRuntime|" + duration );
}

Selenium.prototype._ge_logTSRuntime = function()
{
LOG.debug("> _ge_logTSRuntime||");
var duration = new Date().getTime() - this._ge_getParameter(GE_TSSTARTTIME);
this.doGe_setTCParameter(duration, GE_TSDURATIONTIME);
LOG.debug("< _ge_logTSRuntime|" + duration );
}

Selenium.prototype._ge_generateTSLogLine = function()
{
LOG.debug("> _ge_generateTSLogLine||");
LOG.getit(
this._ge_getParameter(GE_TSID) + " - " + this._ge_getParameter(GE_TSNAME) + ";" +
"Gesamtlaufzeit(sec):;" + ( Math.round(this._ge_getParameter(GE_TSDURATIONTIME)/10)  /100).toLocaleString());
LOG.getit(
this._ge_getParameter(GE_TSID) + " - " + this._ge_getParameter(GE_TSNAME) + ";" +
"Durchschnittlich (sec)/TC:;" + ( Math.round(this._ge_getParameter(GE_TSDURATIONTIME) / this.doGe_getCountedTCinTSForStatus("total")/10)  /100).toLocaleString());
LOG.getit("[TestCase];Total Tests:;" + this.doGe_getCountedTCinTSForStatus("total"));
LOG.getit("[TestCase];Passed Tests:;" + this.doGe_getCountedTCinTSForStatus("passed"));
LOG.getit("[TestCase];Failed Tests:;" + this.doGe_getCountedTCinTSForStatus("failed"));
LOG.getit("[TestCase];Open Tests:;" + this.doGe_getCountedTCinTSForStatus("open"));
LOG.debug("< _ge_generateTSLogLine|");
}

Selenium.prototype._ge_generateTCName = function()
{
LOG.debug("> _ge_generateTCName||");
var nameConfig = 15;
var ret = "";
if ( typeof(globalStoredVars[GE_TCNAMECONFIG]) != "undefined" )
nameConfig = this._ge_getParameter(GE_TCNAMECONFIG);

LOG.debug("nameConfig:" + nameConfig);
if ((1 & nameConfig) == 1)
ret += this._ge_getParameter(GE_TSID) + "-";
if ((2 & nameConfig) == 2)
ret += this._ge_getParameter(GE_TSNAME) + "_";

if ( ((1 & nameConfig) == 1) || ((2 & nameConfig) == 2) && ((4 & nameConfig) == 4) || ((8 & nameConfig) == 8))
{
ret = ret.substr(0, ret.length-1);
ret += "_";
}

if ((4 & nameConfig) == 4)
ret += this._ge_getParameter(GE_TCID) + "-";
if ((8 & nameConfig) == 8)
ret += this._ge_getParameter(GE_TCNAME) + "_";

ret = ret.substr(0, ret.length-1);
LOG.debug("< _ge_generateTCName|" + ret);
return ret;
}

Selenium.prototype._ge_generateTCLogLine = function()
{
LOG.debug("> _ge_generateTCLogLine|" + GE_HEADERCOVERAGE + "|");

var outStr = "";

if ((1 & GE_HEADERCOVERAGE) == 1)
outStr += this._ge_getParameter(GE_TCNO) + ";";

if ((2 & GE_HEADERCOVERAGE) == 2)
outStr += this._ge_generateTCName() + ";";

if ((4 & GE_HEADERCOVERAGE) == 4)
outStr += this._ge_getParameter(GE_TCPRIO) + ";" ;

if ((8 & GE_HEADERCOVERAGE) == 8)
outStr += "Yes;";

if ((16 & GE_HEADERCOVERAGE) == 16)
outStr += this._ge_getTCStatus() + ";" ;

if ((32 & GE_HEADERCOVERAGE) == 32)
outStr += this._ge_getParameter(GE_TCLIMITATION) + ";" ;

if ((64 & GE_HEADERCOVERAGE) == 64)
outStr += this._ge_getParameter(GE_TCPARAMETERS) + ";" ;

if ((128 & GE_HEADERCOVERAGE) == 128)
outStr += this._ge_getParameter(GE_TCDESCRIPTION) + ";" ;

if ((256 & GE_HEADERCOVERAGE) == 256 && this._ge_getTCStatus() == "Failed")
{
var txt = this._ge_getParameter("GE_LASTERROR");
txt=escape(txt).replace(/%0D%0A/,"[BR]");
txt=txt.replace(/%0A%0D/,"[BR]");
txt=unescape(txt);
outStr +=  txt + ";";
}
if ((256 & GE_HEADERCOVERAGE) == 256 && this._ge_getTCStatus() != "Failed" )
outStr += ";";

if ((512 & GE_HEADERCOVERAGE) == 512)
{
outStr += getFormatedTimeDateString("dd.mm.yyyy hh:MM:ss") + ";" +
"Selenium;" +//User
( Math.round(this._ge_getParameter(GE_TCDURATIONTIME)/10)  /100).toLocaleString() + ";";
}

LOG.getit("[TestCase];" + outStr);

LOG.debug("< _ge_generateTCLogLine|");
}

Selenium.prototype._ge_generateLogHeader = function()
{
LOG.debug("> _ge_generateLogHeader|" + GE_HEADERCOVERAGE + "|");
LOG.debug("GE_LOGHEADERGENERATE: "+GE_LOGHEADERGENERATE);
LOG.getit("");
var outStr = "";

if ((1 & GE_HEADERCOVERAGE) == 1)
outStr += "No;";

if ((2 & GE_HEADERCOVERAGE) == 2)
outStr += "ID - Name;";

if ((4 & GE_HEADERCOVERAGE) == 4)
outStr += "Prio;" ;

if ((8 & GE_HEADERCOVERAGE) == 8)
outStr += "Automated;";

if ((16 & GE_HEADERCOVERAGE) == 16)
outStr += "Status;" ;

if ((32 & GE_HEADERCOVERAGE) == 32)
outStr += "Limitation;" ;

if ((64 & GE_HEADERCOVERAGE) == 64)
outStr += "Parameter;" ;

if ((128 & GE_HEADERCOVERAGE) == 128)
outStr += "Action;Expected Result;";

if ((256 & GE_HEADERCOVERAGE) == 256)
outStr += "Actual Result;";

if ((512 & GE_HEADERCOVERAGE) == 512)
{
outStr += "Date of Test;" +
"User;" +
"Comment";
}

LOG.getit("[TestCaseHeader];" + outStr);
GE_LOGHEADERGENERATE = true;

LOG.debug("< _ge_generateLogHeader|");
}

Selenium.prototype.doGe_getCountedTCinTSForStatus = function(status)
{
LOG.debug("> ge_getCountedTCinTSForStatus|" + status + "|");

var ret = 0;

if (status.toLowerCase() == "open")
ret = GE_CountOpenTestsInTS;
if (status.toLowerCase() == "passed")
ret = GE_CountPassedTestsInTS;
if (status.toLowerCase() == "failed")
ret = GE_CountFailedTestsInTS
if (status.toLowerCase() == "total")
ret = GE_CountFailedTestsInTS + GE_CountPassedTestsInTS + GE_CountOpenTestsInTS;

LOG.debug("< ge_getCountedTCinTSForStatus|" + ret);

return ret;
}

Selenium.prototype.doGe_setTimeInMillies = function(varName)
{
LOG.debug("> ge_setTimeInMillies|" + varName + "|");

var tim = new Date().getTime();
this.doGe_setTCParameter(tim,varName);

LOG.debug("< ge_setTimeInMillies|");
}

Selenium.prototype.doGe_logTCComment = function(logType, Message)
{
LOG.debug("> ge_logTCComment|" + logType + "|" + Message);
LOG.getit("[TestCase];[" + logType + "];" + Message);
LOG.debug("< ge_logTCComment|");
}
////////////////////////////

/*
* Values
*/
////////////////////////////
Selenium.prototype.doGe_designateTCParameters = function(varName)
{
LOG.debug("> ge_designateTCParameters|" + varName + "|");

var varArr = varName.split(",");
var TMP = "";

for ( i=0;i< varArr.length;i++)
{
var tmpSingleVarName = varArr[i].trim();

LOG.debug(i+"tmpSingleVarName:'"+tmpSingleVarName+"'");

if (tmpSingleVarName.length>0)
{
if (i == 0)
TMP = tmpSingleVarName+": " + globalStoredVars[tmpSingleVarName];
else
TMP += ", " + tmpSingleVarName+": " + globalStoredVars[tmpSingleVarName];
}
}

this.doGe_setTCParameter(TMP, GE_TCPARAMETERS);

LOG.debug("< ge_designateTCParameters|"+TMP);
}

Selenium.prototype.doGe_appendDesignateTCParameters = function(varName)
{
LOG.debug("> ge_appendDesignateTCParameters|" + varName + "|");

var varArr = varName.split(",");
var TMP = "";

LOG.debug("append "+varArr.length+" param");

for ( i=0;i< varArr.length;i++)
{
var tmpSingleVarName = varArr[i].trim();
LOG.debug(i+"tmpSingleVarName:'"+tmpSingleVarName+"'");

if (tmpSingleVarName.length>0)
{
LOG.debug("appended string "+ (i+1) + ": " + tmpSingleVarName+": " + globalStoredVars[tmpSingleVarName]);
TMP += ", " + tmpSingleVarName+": " + globalStoredVars[tmpSingleVarName];
}
}
this.doGe_setTCParameter((this._ge_getParameter(GE_TCPARAMETERS) + TMP), GE_TCPARAMETERS);

LOG.debug("< ge_appendDesignateTCParameters|"+this._ge_getParameter(GE_TCPARAMETERS));
}

Selenium.prototype._ge_getParameter = function(Param)
{
LOG.debug("> ge_getParameter|" + Param + "|");
if ( typeof(globalStoredVars[Param]) != "undefined" )
return globalStoredVars[Param];
else
return "";
LOG.debug("< ge_getParameter|");
}

Selenium.prototype._ge_getTCStatus = function()
{
LOG.debug("> _ge_getTCStatus||");

var ret = "";

if (this._ge_getParameter(GE_TCSTATUS) == "open" )
ret = "Open";
else if (this._ge_getParameter(GE_TCSTATUS) == "failed" )
ret = "Failed";
else if (this._ge_getParameter(GE_TCSTATUS) == "passed" )
ret = "Passed";

LOG.debug("< _ge_getTCStatus|"+ret);

return ret;
}
////////////////////////////

function getFormatedTimeDateString (FormatString) {
var now = new Date();
var retStr = FormatString;
year = "" + now.getFullYear();
month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }

retStr = retStr.replace("dd",day);
retStr = retStr.replace("mm",month);
retStr = retStr.replace("yyyy",year);

retStr = retStr.replace("hh",hour);
retStr = retStr.replace("MM",minute);
retStr = retStr.replace("ss",second);

return retStr;
}


