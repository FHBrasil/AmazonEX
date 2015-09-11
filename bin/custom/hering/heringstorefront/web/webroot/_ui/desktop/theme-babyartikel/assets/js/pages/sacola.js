// init
$(document).ready(function() {
	$('[data-toggle="popover-bulk"]').popover({
        placement : 'bottom',
		title: 'Sperrgut',
		html: true,
		trigger: 'focus',
		content: 'Dieser Artikel ist Sperrgut und hat m&ouml;glicherweise eine etwas l&auml;ngere Laufzeit. Weitere Informationen zum Thema finden Sie auf der Seite <a href="#">Versand und Lieferung</a>.'
    });
	$('[data-toggle="popover-onstock"]').popover({
        placement : 'bottom',
		title: 'Sofort lieferbar',
		html: true,
		trigger: 'focus',
		content: 'Dieser Artikel ist sofort lieferbar und wird umgehend verschickt, wenn alle Artikel der Bestellung sofort lieferbar sind oder der optionale Teilversand der sofort lieferbaren Artikel gew&auml;hlt wird. Weitere Informationen zum Thema finden Sie auf der Seite  <a href="#">Versand und Lieferung</a>.'
    });
	$('[data-toggle="popover-nostock"]').popover({
        placement : 'bottom',
		title: 'Artikel mit Lieferzeit',
		html: true,
		trigger: 'focus',
		content: 'Dieser Artikel kann erst am angegebenen Datum verschickt werden. Weitere Informationen zum Thema finden Sie auf der Seite  <a href="#">Versand und Lieferung</a>.'
    });
	$('[data-toggle="popover-forwarding"]').popover({
        placement : 'bottom',
		title: 'Speditionslieferung',
		html: true,
		trigger: 'focus',
		content: 'Dieser Artikel wird per Spedition frei Bordsteinkante geliefert. Der Spediteur wird mit Ihnen vorab telefonisch einen Liefertermin vereinbaren. Weitere Informationen zum Thema finden Sie auf der Seite  <a href="#">Versand und Lieferung</a>.'
    });
	
    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput(".calculate-freight");
});