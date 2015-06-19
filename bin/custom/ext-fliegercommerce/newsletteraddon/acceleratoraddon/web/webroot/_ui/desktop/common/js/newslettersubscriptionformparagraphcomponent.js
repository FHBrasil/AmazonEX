var manageFormSubmit = {

    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#newsletterSubscriptionFormComponent').submit(function(event) {

            event.preventDefault();

            var data = $(this).serialize();
            var url = $(this).attr('action');

            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success: function(data) {
                    alert(data);
                    // faz alguma coisa com os dados retornados
                }
            });

            return false;

        });
    }

}

manageFormSubmit.init();