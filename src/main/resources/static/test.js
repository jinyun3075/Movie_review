$('.uploadBtn').click(function(){
    var formData = new FormData();

    var inputFile = $('input[type="file"]');

    var files = inputFile[0].files;

    for(var i=0;i<files.length;i++){
        console.log(files[i]);
        formData.append("uploadFiles",files[i]);
    }
    $.ajax({
        url: '/uploadAjax',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        dataType: 'json',
        success: function(result){
            console.log('성공은함');
            showUploadedImages(result);
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log(textStatus);
        }
    });
});

function showUploadedImages(arr){
    console.log(arr);
    var divArea = $('.uploadResult');

    for(var i=0;i<arr.length;i++){
        divArea.append("<img src='/display?fileName="+arr[i].imageURL+"'>");
    }
}