var flag = false;

// $(document).ready(function () {
//     $(".formdemotarget").submit = function (event) {
//         alert("Replaced submit handler");
//         return true;
//     };
// });


document.getElementById("form1").onsubmit = function (event) {
    alert("Replaced submit handler");
    return true;
};