
function stepperShowHide(element){
    switch(element){
        case "firstStepperBtn" : 
            // making the content of first button is visible and others not visible when it clicked
            document.getElementById("studentDetails").style.display="inline";
            document.getElementById("studentLoginDetails").style.display="none";
            document.getElementById("parentsDetails").style.display="none";
            document.getElementById("studentIdCard").style.display="none";

            // making the first button color is changed and others are not changed when it clicked
            document.getElementById("firstStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("secondStepperBtn").className="btn btn-light stepper-btn";
            document.getElementById("thirdStepperBtn").className="btn btn-light stepper-btn";
            document.getElementById("fourthStepperBtn").className="btn btn-light stepper-btn";

            // making stepper line color changed when first button clicked
            document.getElementById("firstStepperLine").className="stepper-line";
            document.getElementById("secondStepperLine").className="stepper-line";
            document.getElementById("thirdStepperLine").className="stepper-line";
            break;

        case "secondStepperBtn" :
            // making the content of second button is visible and others not visible when it clicked
            document.getElementById("parentsDetails").style.display="none";
            document.getElementById("studentIdCard").style.display="none";
            document.getElementById("studentDetails").style.display="none";
            document.getElementById("studentLoginDetails").style.display="inline";

            // making the second button color is changed and others are not changed when it clicked
            document.getElementById("secondStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("firstStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("thirdStepperBtn").className="btn btn-light stepper-btn";
            document.getElementById("fourthStepperBtn").className="btn btn-light stepper-btn";

            // making stepper line color changed when first button clicked
            document.getElementById("firstStepperLine").className="progressed-stepper-line";
            document.getElementById("secondStepperLine").className="stepper-line";
            document.getElementById("thirdStepperLine").className="stepper-line";
            break;
        case "thirdStepperBtn" :
            // making the content of third button is visible and others not visible when it clicked
            document.getElementById("studentIdCard").style.display="none";
            document.getElementById("studentDetails").style.display="none";
            document.getElementById("parentsDetails").style.display="inline";
            document.getElementById("studentLoginDetails").style.display="none";

            // making the third button color is changed and others are not changed when it clicked
            document.getElementById("thirdStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("firstStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("secondStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("fourthStepperBtn").className="btn btn-light stepper-btn";

            // making stepper line color changed when first button clicked
            document.getElementById("firstStepperLine").className="progressed-stepper-line";
            document.getElementById("secondStepperLine").className="progressed-stepper-line";
            document.getElementById("thirdStepperLine").className="stepper-line";
            break;
        case "fourthStepperBtn" :
            // making the content of third button is visible and others not visible when it clicked
            document.getElementById("studentLoginDetails").style.display="none";
            document.getElementById("studentDetails").style.display="none";
            document.getElementById("parentsDetails").style.display="none";
            document.getElementById("studentIdCard").style.display="inline";

            //making the forth button color is changed and others are not changed when it clicked
            document.getElementById("thirdStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("firstStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("secondStepperBtn").className="btn btn-primary stepper-btn";
            document.getElementById("fourthStepperBtn").className="btn btn-primary stepper-btn";

            // making stepper line color changed when first button clicked
            document.getElementById("firstStepperLine").className="progressed-stepper-line";
            document.getElementById("secondStepperLine").className="progressed-stepper-line";
            document.getElementById("thirdStepperLine").className="progressed-stepper-line";
    }
}

