$(":file").change(function () {
    //alert("Hello")
    if (this.files && this.files[0]) {
        var reader = new FileReader();
        reader.onload = imageIsLoaded;
        reader.readAsDataURL(this.files[0]);
    }
});

function imageIsLoaded(e) {

    alert(e.target.result)

    $('#myImg').attr('src', e.target.result);
    $('#myImg').fadeIn();
};