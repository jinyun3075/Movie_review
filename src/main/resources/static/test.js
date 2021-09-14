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

$(".uploadResult").on("click", ".removeBtn", function(e){

    var target = $(this);
    var fileName = target.data("name");
    var targetDiv = target.closest("div");
    console.log(targetDiv);

    console.log(fileName);

    $.post('/removeFile', {fileName:fileName}, function(result)){
        console.log(result);
        if(result==true){
        targetDiv.remove();
       }
    }
});

$("document").ready(function(e){
    var regex = new RegExp("(.*?)\\.(exe|sh|zip|alz|tiff)$");
    var maxSize = 10485760; // 10MB

    function checkExtension(fileName, fileSize){
        if(fileSize>=maxSize){
            alert("파일 사이즈 초과");
            return false;
        }

        if(regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없습니다.");
            return false;
        }
        return true;
    }
    $(".custom-file-input").on("change", function(){
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);

        var formData = new FormData();

        var inputFile = $(this);

        var files = inputFile[0].files;

        var appended = false;

        for(var i=0;i<files.length;i++){
            if(!checkExtension(files[i].name,files[i].size)){
                return false;
            }

            console.log(files[i]);
            formData.append("uploadFiles",files[i]);
            appended=true;
        }

        if(!appended) {return;}

        for(var value of formData.values()){
            console.log(value);
        }

        $.ajax({
            url: '/uploadAjax',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: 'json',
            success: function(result){
                console.log(result);
            },
            error: function(jqXHR,textStatus,errorThrown){
                console.log(textStatus);
            }
        }); // ajax 종료
    }) // change 이벤트 종료
});

function showResult(uploadResultArr){
    var uploadUL = $(".uploadResult ul");

     var str ="";
     $(uploadResultArr).each(function(i, obj){
        str+="<li data-name='"+obj.fileName+"'data-path='"+obj.folderPath+"'data-uuid='"+obj.uuid+"'>";
        str+="<div>";
        str+="<button type='button' data-file=\'"+obj.imageURL+"\'";
        str+="class='btn-warning btn-sm'>X</button><br>";
        str+="<img src='/display?fileName=" +obj.thumbnailURL+"'>";
        str+="</div>";
        str+="</li>"
     });
     uploadUl.append(str);
}

$('.uploadResult').on("click","li button", function(e){
    console.log("delete File");

    var targetFile = $(this).data("file");
    var targetLi = $(this).closest("li");
    $.ajax({
        url: '/removeFile',
        data: {fileName: targetFile},
        dataType:'text',
        type: 'POST',
        success: function(result){
            alert(result);
            targetLi.remove();
        }
    })
})

$('.btn-primary').on("click", function(e){
    e.preventDefault();

    var str="";

    $('.uploadResult li').each(function(i,obj){
        var target = $(obj);
        str+="<input type='hidden' name='imageDtoList["+i+"].imgName' value='"+target.data('name')+"'>";
        str+="<input type='hidden' name='imageDtoList["+i+"].path' value='"+target.data('path')+"'>";
        str+="<input type='hidden' name='imageDtoList["+i+"].uuid' value='"+target.data('uuid')+"'>";
    });
    $('.box').html(str);


})