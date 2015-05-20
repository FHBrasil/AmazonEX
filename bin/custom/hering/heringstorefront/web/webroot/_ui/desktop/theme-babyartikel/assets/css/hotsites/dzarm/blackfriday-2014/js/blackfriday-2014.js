(function() {
	
	var caminho = window.location.pathname;
	
	var HotsiteBlackFriday2014 = function() {
		var self = this;
		this.elCountdownWrap = document.getElementById('hotsite-blackfriday-2014-contador');

		this.evtUpdateCountdown = function(e) {
			self.elCountdownWrap.innerHTML = '<div class="numero dias"><span>' + e.days + '</span>Dias';
			self.elCountdownWrap.innerHTML += '<div class="separa separa-dias">:</div><div class="numero horas"><span>' + (e.hours<10 ? "0"+e.hours : e.hours) + '</span>Horas';
			self.elCountdownWrap.innerHTML += '<div class="separa separa-horas">:</div><div class="numero minutos"><span>' + (e.minutes<10 ? "0"+e.minutes : e.minutes) + '</span>Minutos';
			self.elCountdownWrap.innerHTML += '<div class="separa separa-minutos">:</div><div class="numero segundos"><span>' + (e.seconds<10 ? "0"+e.seconds : e.seconds) + '</span>Segundos';
		}

		countdown(this.evtUpdateCountdown, new Date(2015, 11, 27) );
	};
	
	if(caminho=="/store/dzarm-black-friday-2014"){
	// só roda se for a pagina do black friday
	var HBF = new HotsiteBlackFriday2014();
	}


$("#command").submit(function () {
	var nome = $("#command > input[name=nome]").val();
	var email = $("#command > input[name=email]").val();
	if (nome == "") {
		return false;
	} else if (email == "") {
		return false;
	} else {
		return true;
	}
});

if(caminho!="/store/dzarm-black-friday-2014"){
	$('body').attr('style', 'background: #FFF !important;');
}

$("#command > input[name=name]").attr('placeholder','Nome');
$("#command > input[name=email]").attr('placeholder','E-mail');

})();