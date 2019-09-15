function cliAge(input_date) {
    var date_array = input_date.split("-");

    var date = new Date();
    date.setYear(date_array[0]);
    date.setMonth(date_array[1] - 1); // month démarre à 0
    date.setDate(date_array[2]);

    var age = Math.floor(((new Date).getTime() - date.getTime()) / (365.24 * 24 * 3600 * 1000));

    if (age < 18) {
        document.getElementById("tutor").style.display = "block";
    } else {
        document.getElementById("tutor").style.display = "none";
    }

}