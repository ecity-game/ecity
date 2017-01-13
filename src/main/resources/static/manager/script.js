$(document).ready(function() {

    var login = "";
    var password = "";

    $('#login').on('change', function(){
        return login = $(".form-authorization #login").val(); 
    });

    $('#password').on('change', function(){
       return password = $(".form-authorization #password").val();
    });

    // Авторизация и получение массива с городами
    $(".authorization").on('click', function() {

      if ( login || password ) {

          $.ajax({
            xhrFields: {
              withCredentials: true
            },
            beforeSend: function() {
              $('body').append('<div class="loader"><img src="img/loading.gif"></div>');
            },
            headers: {
                'Authorization': 'Basic ' + btoa(login+':'+password)
            },
            url: "http://ecity.org.ua:8080/admin/cities",
            dataType: 'json',
            type: 'POST',
            success: function(data) {
              $(".wrapp-authorization").hide();
              data.forEach(function(item) {
                $(".table").append('<div class="item-city-wrap"><div class="item-city"><input type="text" value="'+ item.id +'" class="id-city" readonly><input type="text" value="'+ item.name +'" class="name-city" readonly><input type="text" value="' + item.regionId +'" class="region-city" readonly><input type="text" value="' + item.latitude +'" class="positionX-city" readonly><input type="text" value="' + item.longitude +'" class="positionY-city" readonly><input type="text" value="' + item.establishment +'" class="establishment-city" readonly><input type="text" value="' + item.population +'" class="population-city" readonly><textarea class="info-city" readonly>'+ item.url +'</textarea></div><span class="edit"></span><span class="save"></span><span class="delete"></span></div>');
              });
            },
            error:function() { 
              alert('Данный логин или пароль не найден! Повторите попытку');
              $(".form-authorization #login").val('');
              $(".form-authorization #password").val('');
            },
            complete: function(){
              $('.loader').remove();
            }

          });

      }

      else { alert("Все поля должны быть заполнены. Допускается только буквы английского алфовита")}

    });



    // редактирование строки города
    $(".table").on('click', 'span.edit', function() {
        
        $(this).siblings(".item-city").children("input:not(.id-city), textarea").removeAttr("readonly").addClass('active');
        $(this).siblings(".save").addClass('active');
        $(this).hide();

    });


    // сохранение редактирования или добавление нового города в базе
    $(".table").on('click', 'span.save', function() {
          
          var activeSpan = $(this);
          var id = $(this).siblings(".item-city").children("input.id-city").val();
          var city = $(this).siblings(".item-city").children("input.name-city").val();
          var region = $(this).siblings(".item-city").children("input.region-city").val();
          var position_x = $(this).siblings(".item-city").children("input.positionX-city").val();
          var position_y = $(this).siblings(".item-city").children("input.positionY-city").val();
          var establishment = $(this).siblings(".item-city").children("input.establishment-city").val();
          var population = $(this).siblings(".item-city").children("input.population-city").val();
          var description = $(this).siblings(".item-city").children("textarea").val();
          var inspectionCity = /^[-а-яА-ЯёЁa-zA-Z\s]+$/;
          var inspectionNumber = /^[0-9]+$/;

          if (( city == "" ) || ( establishment == "" ) ||  ( population == "" )  ||  ( region == "" ) || ( position_x == "" ) || ( position_y == "" ) || ( description == "" ) ) {

            alert("Заполните все поля!");

          }

          else if ( city.search(inspectionCity) !== 0 ) {

            alert("Вы некорректно заполнили поле с названием города!");

          }

          else if ( (position_x.search(inspectionNumber) !== 0 ) || (position_x < 0) || (position_x > 100) ) {

            alert("Позиция X может быть только числом от 0 до 100!");

          }

          else if ( (position_y.search(inspectionNumber) !== 0 ) || (position_y < 0) || (position_y > 100) ) {

            alert("Позиция Y может быть только числом от 0 до 100!");

          }

          else if ( (establishment.search(inspectionNumber) !== 0 ) || (establishment < 0) || (establishment > 2000) ) {

            alert("Вы ввели некорректную дату основания города!");

          }

          else if ( (population.search(inspectionNumber) !== 0 ) || (population < 0) ) {

            alert("Численность населения может быть только положительным числом!");

          }
 
          else {


            if ( $(this).hasClass('add-city') ) {

                //создаем новый город
                $.ajax({
                xhrFields: {
                  withCredentials: true
                },
                headers: {
                    'Authorization': 'Basic ' + btoa(login+':'+password)
                },
                url: "http://ecity.org.ua:8080/admin/city/add",
                dataType: 'json',
                type: 'POST',
                data: {"name":city,"regionId":region,"longitude":position_y,"latitude":position_x,"population":population,"establishment":establishment,"url":description},
                success: function(data) {

                    var code = data.adminPanelStatus;
                    var newId = data.cityId;

                    if (code.code == 100 ) {

                      activeSpan.siblings(".item-city").children("input:not(.id-city), textarea").removeAttr("readonly").removeClass('active');
                      activeSpan.siblings(".edit").show();
                      activeSpan.removeClass('active add-city');
                      activeSpan.siblings("span.delete").removeClass('new-city');
                      activeSpan.parent().removeClass('new-city');
                      activeSpan.siblings(".item-city").children("input.id-city").val(newId);
                      alert("Город успешно создан!");

                    }

                    else if (code.code == 103 ) {

                      alert("Город с таким названием уже существует!");

                    }

                    else if (code.code == 105 ) {

                      alert("Вы ввели некорректную дату основания города!");

                    }

                    else {

                      alert("Произошел сбой и город не создан!");

                    }

                  }
                });

                

            }

            else {

                //редактирует существующий город
                $.ajax({
                  xhrFields: {
                    withCredentials: true
                  },
                  headers: {
                      'Authorization': 'Basic ' + btoa(login+':'+password)
                  },
                  url: "http://ecity.org.ua:8080/admin/city/edit",
                  dataType: 'json',
                  type: 'POST',
                  data: {"id":id,"name":city,"regionId":region,"longitude":position_y,"latitude":position_x,"population":population,"establishment":establishment,"url":description},
                  success: function(data) { 

                    var code = data.adminPanelStatus;

                    if (code.code == 101 ) {

                      activeSpan.siblings(".item-city").children("input:not(.id-city), textarea").removeAttr("readonly").removeClass('active');
                      activeSpan.siblings(".edit").show();
                      activeSpan.removeClass('active');
                      alert("Город успешно отредактирован!");

                    }

                    else if (code.code == 103 ) {

                      alert("Город с таким названием уже существует!");

                    }

                    else if (code.code == 104 ) {

                      alert("Город не найден!");

                    }

                    else if (code.code == 105 ) {

                      alert("Вы ввели некорректную дату основания города!");

                    }

                    else {

                      alert("Произошел сбой и город не изменен!");

                    }
                      
                  }
                });

            }

          } 

    });

    //удаление города/новой строки 
    $(".table").on('click', 'span.delete', function() {
      
        var activeSpan = $(this);
        var id = $(this).siblings(".item-city").children("input.id-city").val();

        if ( !$(this).hasClass('new-city') ) {

          if ( confirm("Вы пытаетесь удалить город! После выполнения этого действия, вся информация будет безвозвратно удалена из базы. Продолжить удаление города?") ) {

            //удаляем город в базе
            $.ajax({
              xhrFields: {
                withCredentials: true
              },
              headers: {
                  'Authorization': 'Basic ' + btoa(login+':'+password)
              },
              url: "http://ecity.org.ua:8080/admin/city/delete/" + id,
              dataType: 'json',
              type: 'POST',
              success: function(data) {

                var code = data.adminPanelStatus;

                if (code.code == 102 ) {

                  alert("Город успешно удален!");
                  activeSpan.parent().remove();

                }

                else if (code.code == 104 ) {

                  alert("Город не найден!");

                }
                  

                else {

                  alert("Произошел сбой и город не удален!");

                }
                    
              }
            });

          }

        }

        else {

            //удаляем пустую строку на странице
             activeSpan.parent().remove();

        } 

 
    });

    //промотка страницы вниз
    function scroll_to_bottom(speed) {
      var height= $("body").height(); 
      $("html,body").animate({"scrollTop":height},speed); 
    }

    // добавление нового города в таблице на странице
    $(".add-city").on('click', function() {

        scroll_to_bottom(500);
        var new_city = $('<div class="item-city-wrap new-city"><div class="item-city"><input type="text" value="" class="id-city" readonly><input type="text" value="" class="name-city" readonly><input type="text" value="" class="region-city" readonly><input type="text" value="" class="positionX-city" readonly><input type="text" value="" class="positionY-city" readonly><input type="text" value="" class="establishment-city" readonly><input type="text" value="" class="population-city" readonly><textarea class="info-city" readonly></textarea></div><span class="edit"></span><span class="save add-city"></span><span class="delete new-city"></span></div>');
        
        $(".table").append(new_city);
        $(".item-city-wrap.new-city .item-city").children("input:not(.id-city), textarea").removeAttr("readonly").addClass('active');
        $(".item-city-wrap.new-city .save").addClass('active');
        $(".item-city-wrap.new-city .edit").hide();

    });

    // функция поиска города в таблице
    function search() {
        
        var object = $('.item-city'); 
        var search = $('#spterm').val().toLowerCase(); 
        var marginTop = $('.header').height();
        var countCity = 0;

        $.each(object, function(){

          var idCity = $(this).children('input.id-city').val().toLowerCase(); 
          var idName = $(this).children('input.name-city').val().toLowerCase();
          
          if (( idCity == search ) || ( idName == search )) {

            var height = $(this).children('input.id-city').offset().top - marginTop -5; 
            $("html,body").animate({"scrollTop":height}, 800); 
            countCity += 1;
          }

          return countCity;
        });

        if (countCity == 0) { 
          alert("Город с таким названием или id не найден!");
        }

    }

    // поиск города в таблице при клике на кнопку поиска
    $(".search .button").on('click', function() {
        search();
    });

    // поиск города в таблице при нажатии энтера в поле ввода
    $('input').keydown(function(e) {
      if(e.keyCode === 13) {
        search();
      }
    });


});