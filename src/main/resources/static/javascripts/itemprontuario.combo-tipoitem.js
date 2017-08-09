var Iaff = Iaff || {};

Iaff.ComboTipoItem = (function() {
	
	function ComboTipoItem() {
		this.combo = $('#tipoItem');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboTipoItem.prototype.iniciar = function() {
		this.combo.on('change', onTipoItemAlterado.bind(this));
	}
	
	function onTipoItemAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboTipoItem;
	
}());


Iaff.ComboTipoItemProntuario = (function() {
	
	function ComboTipoItemProntuario(comboTipoItem) {
		this.comboTipoItem = comboTipoItem;
		this.combo = $('#tipoItemProntuario');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenTipoItemSelecionado = $('#inputHiddenTipoItemSelecionado');
	}
	
	ComboTipoItemProntuario.prototype.iniciar = function() {
		reset.call(this);
		this.comboTipoItem.on('alterado', onTipoItemAlterado.bind(this));
		var codigoTipoItem = this.comboTipoItem.combo.val();
		inicializarTiposItensProntuario.call(this, codigoTipoItem);
	}
	
	function onTipoItemAlterado(evento, codigoTipoItem) {
		this.inputHiddenTipoItemSelecionado.val('');
		inicializarTiposItensProntuario.call(this, codigoTipoItem);
	}
	
	function inicializarTiposItensProntuario(codigoTipoItem) {
		if (codigoTipoItem) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'tipoItem': codigoTipoItem, 'codigoGrupo': this.combo.data('codgrupo')}, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarTiposItensProntuarioFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarTiposItensProntuarioFinalizado(tiposItensProntuario) {
		var options = [];
		tiposItensProntuario.forEach(function(tipoItemProntuario) {
			options.push('<option value="' + tipoItemProntuario.codigo + '">' + tipoItemProntuario.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoTipoItemSelecionado = this.inputHiddenTipoItemSelecionado.val();
		if (codigoTipoItemSelecionado) {
			this.combo.val(codigoTipoItemSelecionado);
		}
		
		
		// teste
		/*
		var tipoCampo = this.combo.val();
		var vtxt = document.getElementById('valortxt');
		var vcom = document.getElementById('valorcombo');
		if(tipoCampo == '14'){
			vcom.removeAttribute('hidden');
			vtxt.setAttribute('hidden', true);
			//d1.insertAdjacentHTML('beforeend', '<input id="controle" type="text" class="form-control" th:field="*{valor}"/>');
		} else {
			vtxt.removeAttribute('hidden');
			vcom.setAttribute('hidden', true);
		}
		*/
		// fim teste
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione o campo do prontuário</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	return ComboTipoItemProntuario;
	
}());


$(function() {
	
	var comboTipoItem = new Iaff.ComboTipoItem();
	comboTipoItem.iniciar();
	
	var comboTipoItemProntuario = new Iaff.ComboTipoItemProntuario(comboTipoItem);
	comboTipoItemProntuario.iniciar();
	
});

