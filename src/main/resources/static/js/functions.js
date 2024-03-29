function cliAge(input_date) {
    var date_array = input_date.split("-");

    var date = new Date();
    date.setYear(date_array[0]);
    date.setMonth(date_array[1] - 1); // month démarre à 0
    date.setDate(date_array[2]);

    var age = Math.floor(((new Date).getTime() - date.getTime()) / (365.24 * 24 * 3600 * 1000));

    return age;

};

function tutor(input_date) {

    var age = cliAge(input_date);

    if (age < 18) {
        document.getElementById("tutor").style.display = "block";
    } else {
        document.getElementById("tutor").style.display = "none";
    }
}

function putResultMM(mm) {
    document.getElementById("resultMM").innerHTML = checkNumberDigits(mm);
    return;
};

function putResultHH(hh) {
    document.getElementById("resultHH").innerHTML = hh;
    return;
};

function checkNumberDigits(myNumber) {
    myNumber = myNumber.toString();
    if (myNumber.length < 2) {
        return "0" + myNumber;
    }
    return myNumber;
};

function cliAgeValidate(input_date, client_id) {

    var age = cliAge(input_date);
    var birthdateId = 'birthdate' + client_id;
    //alert("js : " + birthdateId + " / " + age);

    document.getElementById(birthdateId).innerText = age;
};