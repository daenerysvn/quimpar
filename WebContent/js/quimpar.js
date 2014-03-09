/**
 * Javascript File
 */

/**
 * On Document Load
 */


/**
 * Waiting modal dialog for Ajax Operations
 */
function ajaxRequestContainsErrors() {
		return document.getElementById("maximumSeverity").value == "2";
	}
	function closeModalPanel(panelId) {
		if (!ajaxRequestContainsErrors()){ 
			Richfaces.hideModalPanel(panelId);
		}else{
			Richfaces.showModalPanel(panelId);
		}
	}


var waitDialogShown = false;
var useTimerBeforeShowWaitDialog = true;
var waitDialogTimeout = 50;
var waitDialogTimer = false;
 
function showWaitDialog() {
    //avoid attempt to show it if it is already shown
    if (!waitDialogShown) {
        Richfaces.showModalPanel('WaitDialog');
        waitDialogShown = true;
    }
}
 
function onRequestStart() {
    if (useTimerBeforeShowWaitDialog) {
        waitDialogTimer = setTimeout("showWaitDialog();", waitDialogTimeout);
    } else {
        showWaitDialog();
    }
}

function onRequestEnd() {
	if (waitDialogShown) {
		Richfaces.hideModalPanel('WaitDialog');
		waitDialogShown = false;
	} else if (useTimerBeforeShowWaitDialog && waitDialogTimer) {
		clearTimeout(waitDialogTimer);
	}
}

function onRequestEndForm(formId, lenght, validateEntireForm) {
	if (waitDialogShown) {
		Richfaces.hideModalPanel('WaitDialog');
		waitDialogShown = false;
	} else if (useTimerBeforeShowWaitDialog && waitDialogTimer) {
		clearTimeout(waitDialogTimer);
	}
	if (formId != null) {
		if (validateEntireForm == true) {
			recorrerForm(formId, lenght);
		}
	}
}

function conf(){
	if(!confirm('Esta seguro que desea cancelar la operación')){
		return false;
	}else{
		onRequestStart();
	}
}