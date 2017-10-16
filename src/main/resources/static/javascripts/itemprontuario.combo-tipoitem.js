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
		this.inputHiddenTipoItemProntuarioSelecionado = $('#inputHiddenTipoItemProntuarioSelecionado');
		
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboTipoItemProntuario.prototype.iniciar = function() {
		reset.call(this);
		
		// inserido para controle da opção do campo
		this.combo.on('change', onTipoItemProntuarioAlterado.bind(this));
		
		this.comboTipoItem.on('alterado', onTipoItemAlterado.bind(this));
		var codigoTipoItem = this.comboTipoItem.combo.val();
		inicializarTiposItensProntuario.call(this, codigoTipoItem);
	}
	
	function onTipoItemProntuarioAlterado() {
		this.emitter.trigger('itemalterado', this.combo.val());
	}
	
	function onTipoItemAlterado(evento, codigoTipoItem) {
		this.inputHiddenTipoItemProntuarioSelecionado.val('');
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
		
		if(tiposItensProntuario.length > 0){
			tiposItensProntuario.forEach(function(tipoItemProntuario) {
				options.push('<option value="' + tipoItemProntuario.codigo + '">' + tipoItemProntuario.nome + '</option>');
			});
			
			this.combo.html(options.join(''));
			this.combo.removeAttr('disabled');
			
			var codigoTipoItemProntuarioSelecionado = this.inputHiddenTipoItemProntuarioSelecionado.val();
			if (codigoTipoItemProntuarioSelecionado) {
				this.combo.val(codigoTipoItemProntuarioSelecionado);
			} else {
				// this.combo.val(1);
			}
			
			onTipoItemProntuarioAlterado.call(this);
		}

	}
	
	function reset() {
		
		// this.combo.html('<option value="">Selecione a Categoria</option>');

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


Iaff.ComboOpcaoCampoItem = (function() {
	
	function ComboOpcaoCampoItem(comboTipoItemProntuario) {
		this.comboTipoItemProntuario = comboTipoItemProntuario;
		this.combo = $('#opcaoCampoItem');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenOpcaoItemSelecionada = $('#inputHiddenOpcaoItemSelecionada');
	}
	
	ComboOpcaoCampoItem.prototype.iniciar = function() {
		reset.call(this);
		
		this.comboTipoItemProntuario.on('itemalterado', onTipoItemProntuarioAlterado.bind(this));
		var codigoTipoItemProntuario = this.comboTipoItemProntuario.combo.val();
		inicializarOpcoesCampoItem.call(this, codigoTipoItemProntuario);
	}
	
	function onTipoItemProntuarioAlterado(evento, codigoTipoItemProntuario) {
		// this.inputHiddenOpcaoItemSelecionada.val('');
		inicializarOpcoesCampoItem.call(this, codigoTipoItemProntuario);
	}
	
	function inicializarOpcoesCampoItem(codigoTipoItemProntuario) {
		if (codigoTipoItemProntuario) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'codigoTipoItemProntuario': codigoTipoItemProntuario}, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarOpcoesCampoItemFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarOpcoesCampoItemFinalizado(opcoesCampoItem) {
		var options = [];
		
		if(opcoesCampoItem.length > 0){
			opcoesCampoItem.forEach(function(opcaoCampoItem) {
				options.push('<option value="' + opcaoCampoItem.codigo + '">' + opcaoCampoItem.nome + '</option>');
			});
			
			this.combo.html(options.join(''));
			this.combo.removeAttr('disabled');
			
			var codigoOpcaoCampoItemSelecionada = this.inputHiddenOpcaoItemSelecionada.val();
			if (codigoOpcaoCampoItemSelecionada >= opcoesCampoItem[0].codigo &&
				codigoOpcaoCampoItemSelecionada <= opcoesCampoItem[opcoesCampoItem.length-1].codigo) {
				this.combo.val(codigoOpcaoCampoItemSelecionada);
			} else {
				// this.combo.val(1);
			}
		}
	}
	
	
	function reset() {

		// this.combo.html('<option value="">Selecione a Opção</option>');

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
	
	return ComboOpcaoCampoItem;
	
}());



$(function() {
	
	var comboTipoItem = new Iaff.ComboTipoItem();
	comboTipoItem.iniciar();
	
	var comboTipoItemProntuario = new Iaff.ComboTipoItemProntuario(comboTipoItem);
	comboTipoItemProntuario.iniciar();
	
	var comboOpcaoCampoItem = new Iaff.ComboOpcaoCampoItem(comboTipoItemProntuario);
	comboOpcaoCampoItem.iniciar();

});


