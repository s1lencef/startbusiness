(function () {
    var $container = $(document.getElementById('form4'));

    var $tooltip = $('#tooltip');

    var
        $region = $container.find('[name="subject"]'),
        $city = $container.find('[name="locality"]'),
        $street = $container.find('[name="street"]'),
        $building = $container.find('[name="building"]');
    $()
        .add($region)
        .add($city)
        .add($street)
        .add($building)
        .fias({
            parentInput: $container.find('.request-form-page'),
            verify: true,

            check: function (obj) {
                var $input = $(this);

                if (obj) {
                    setLabel($input, obj.type);
                    $tooltip.hide();
                }
                else {
                    showError($input, 'Ошибка');
                }
            },
            checkBefore: function () {
                var $input = $(this);

                if (!$.trim($input.val())) {
                    $tooltip.hide();
                    return false;
                }
            }
        });

    $region.fias('type', $.fias.type.region);
    $city.fias('type', $.fias.type.city);

    $street.fias('type', $.fias.type.street);
    $building.fias('type', $.fias.type.building);

    $city.fias('withParents', true);
    $street.fias('withParents', true);

    // Отключаем проверку введённых данных для строений
    $building.fias('verify', false);



    function setLabel($input, text) {
        text = text.charAt(0).toUpperCase() + text.substr(1).toLowerCase();
        $input.parent().find('label').text(text);
    }

    function showError($input, message) {
        $tooltip.find('span').text(message);

        var inputOffset = $input.offset(),
            inputWidth = $input.outerWidth(),
            inputHeight = $input.outerHeight();

        var tooltipHeight = $tooltip.outerHeight();
        var tooltipWidth = $tooltip.outerWidth();

        $tooltip.css({
            left: (inputOffset.left + inputWidth - tooltipWidth) + 'px',
            top: (inputOffset.top + (inputHeight - tooltipHeight) / 2 - 1) + 'px'
        });

        $tooltip.fadeIn();
    }
})();