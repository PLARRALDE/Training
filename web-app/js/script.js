/**
 * Created by plarralde on 20/8/15.
 */

$().ready(function(){
    $("#txtUser").keyup(buscarAJAX);
})

function buscarAJAX(){
    var urls = "https://api.mercadolibre.com/sites/MLA/search?q=" + $("#txtUser").val();

    $.ajax({

        url:urls,
        dataType: "json",
        method:'GET',

        success:function(array){
            alert(array.length)
        },
        error:function(error){
            alert("error")
        }
    })
}