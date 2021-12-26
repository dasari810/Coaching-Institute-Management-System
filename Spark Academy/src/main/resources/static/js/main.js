
function postRequest(url, data) {
    $.ajax({
        url: url,
        type: "post",
        data: data,
        success: function(data, status, xhr){
            location.reload();
        },
        error: function(xhr, status, err){
            document.getElementById("error").innerHTML = xhr.responseText;
        }
    });
}

function getRequest(url) {
    $.ajax({
        url: url,
        type: "get",
        success: function(data, status, xhr){
            location.reload();
        },
        error: function(xhr, status, err){
            alert(xhr.responseText);
        }
    });
}

 $('#datatable').DataTable( {
	 fixedHeader: true
});

(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

// password toggle 

$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
});

