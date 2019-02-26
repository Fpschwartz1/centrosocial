Brewer= Brewer || {};

Brewer.Desmarcar = (function() {
	
	function Desmarcar() {
		this.desmarcarBtn = $('.js-desmarcar-btn');
	}
	
	Desmarcar.prototype.iniciar = function() {
		this.desmarcarBtn.on('click', onDesmarcarBtnClicado.bind(this));
	}
	
	function onDesmarcarBtnClicado(event) {
		var botaoClicado = $(event.currentTarget);
		var url = botaoClicado.data('url');

		if (url.length > 0) {
			$.ajax({
				url: url,
				method: 'GET',
				/*
				data: {
					codigos: codigos,
					status: status
				},
				*/ 
				success: function() {
					window.location.reload();
				}
			});
			
		}

	}
	
	return Desmarcar;
	
}());

$(function() {
	var agendaDesmarcar = new Brewer.Desmarcar();
	agendaDesmarcar.iniciar();
});