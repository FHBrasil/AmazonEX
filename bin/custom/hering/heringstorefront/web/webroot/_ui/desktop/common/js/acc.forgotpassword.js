ACC.forgotpassword = {
    
    bindAll : function() {
        this.bindForgotPasswordLink($('.password-forgotten'));
        this.bindForgotPasswordLink($('.password-forgotten-over'));
    },
    
    bindForgotPasswordLink : function(link) {
        link.click(function() {
            
            ACC.forgotpassword.openForgotPassword(link.data('url'));
            // ACC.forgotpassword.openLightbox(link.data('url'));
        });
    },
    
    openForgotPassword : function(url) {
        $
                .get(url)
                .done(
                        function(data) {
                            $
                                    .colorbox({
                                        html : data,
                                        width : 500,
                                        height : false,
                                        overlayClose : false,
                                        onOpen : function() {
                                            $('#validEmail').remove();
                                        },
                                        onComplete : function() {
                                            var forgottenPwdForm = $('#forgottenPwdForm');
                                            forgottenPwdForm
                                                    .ajaxForm({
                                                        success : function(data) {
                                                            if ($(data)
                                                                    .closest(
                                                                            '#validEmail').length) {
                                                                
                                                                if ($('#validEmail').length === 0) {
                                                                    $(
                                                                            '#globalMessages')
                                                                            .append(
                                                                                    data);
                                                                }
                                                                $.colorbox
                                                                        .close();
                                                            } else {
                                                                
                                                                $(
                                                                        "#forgottenPwdForm .control-group")
                                                                        .replaceWith(
                                                                                $(
                                                                                        data)
                                                                                        .find(
                                                                                                '.control-group'));
                                                                $.colorbox
                                                                        .resize();
                                                            }
                                                        }
                                                    });
                                            ACC.common
                                                    .refreshScreenReaderBuffer();
                                        },
                                        onClosed : function() {
                                            ACC.common
                                                    .refreshScreenReaderBuffer();
                                        }
                                    });
                            try {
                                $('#forgottenPwd\\.email').val(
                                        $('#j_username').val());
                            } catch (error) {
                            }
                        });
    }
};

$(document).ready(
        function() {
            if ($("#forgotPassword").val() == 'true') {
                ACC.forgotpassword.openForgotPassword($('.password-forgotten')
                        .data('url'));
            }
            ACC.forgotpassword.bindAll();
        });