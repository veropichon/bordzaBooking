function putResultMM(mm) {
    document.getElementById("resultMM").innerHTML = checkNumberDigits(mm);
    return;
}
function putResultHH(hh) {
    document.getElementById("resultHH").innerHTML = hh;
    return;
}
function checkNumberDigits(myNumber)
{
    myNumber = myNumber.toString();
    if ( myNumber.length < 2 ) { return "0" + myNumber; }
    return myNumber;
}