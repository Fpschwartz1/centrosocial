var Iaff = Iaff || {};

Iaff.PesquisaRapidaPaciente = (function() {
	
	function PesquisaRapidaPaciente() {
		this.pesquisaRapidaPacientesModal = $('#pesquisaRapidaPacientes');
		this.nomeInput = $('#nomePacienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pacientes-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaPacientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-paciente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaPaciente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaPacientesModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaPacientesModal.on('hide.bs.modal', onModalClose.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onModalClose() {
		this.nomeInput.val('');
		this.mensagemErro.addClass('hidden');
		this.containerTabelaPesquisa.html("");
	}	
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		if(this.nomeInput.val().length >= 3) {
		
			$.ajax({
				url: this.pesquisaRapidaPacientesModal.find('form').attr('action'),
				method: 'GET',
				contentType: 'application/json',
				data: {
					nome: this.nomeInput.val()
				}, 
				success: onPesquisaConcluida.bind(this),
				error: onErroPesquisa.bind(this)
			});
			
		} else onErroPesquisa.call(this);
	
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPacientePesquisaRapida = new Iaff.TabelaPacientePesquisaRapida(this.pesquisaRapidaPacientesModal);
		tabelaPacientePesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaPaciente;
	
}());

Iaff.TabelaPacientePesquisaRapida = (function() {
	
	function TabelaPacientePesquisaRapida(modal) {
		this.modalPaciente = modal;
		this.Paciente = $('.js-paciente-pesquisa-rapida');
	}
	
	TabelaPacientePesquisaRapida.prototype.iniciar = function() {
		this.Paciente.on('click', onPacienteSelecionado.bind(this));
	}
	
	function onPacienteSelecionado(evento) {
		this.modalPaciente.modal('hide');
		
		var PacienteSelecionado = $(evento.currentTarget);
		
		// identificação do contextpath (csiaff) para a pesquisa de pacientes
		var url2 = this.modalPaciente.find('form').attr('action');
		var pos = url2.lastIndexOf("/");
		
		// envia solicitacao ao MarcacaoController
		$.ajax({
			url: url2.substr(0, pos) + '/marcacao',
			method: 'GET',
			contentType: 'application/json',
			data: {
				codigoAgenda: $('#codigoAgendaModal').val(),
				codigoPaciente: PacienteSelecionado.data('codigo')
			}, 
			success: function() {
				window.location.reload();
			},
			error: onErroPesquisa.bind(this)
		});
		
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return TabelaPacientePesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaPaciente = new Iaff.PesquisaRapidaPaciente();
	pesquisaRapidaPaciente.iniciar();
});