<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    function prepareColorNameFilter() {
        var q = $('#q').val();
        var t = $('#t').val();
        var n = $('#n').val();
        var i = q.indexOf(":");

        q = i >= 0 ? q.substring(i + 1) : q;
        n = (t.length > 0 ? (t + ' ' + n) : n) + ':' + q;

        $('#q').val(n);
    }
</script>
