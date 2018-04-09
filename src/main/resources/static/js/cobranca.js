//console.log("cobranca.js")
$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget) ;
			  var codigo = button.data('codigo') ;
			  var descricao = button.data('descricao');
			   var modal = $(this)
			   var form = modal.find('form');
			   var action = form.data('url-base');
			 if(!action.endsWith('/')){
				 action += '/';
			 }
			 form.attr('action',action+codigo)
			 modal.find('.modal-body span').html('Deseja excluir o título <strong>'+descricao+'</strong>?');
			});

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal:',', thousands:'.', allowZero:false});
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		})
		console.log(response);
		response.done(function(e){
			var codigo = botaoReceber.data('codigo');
			$('[data-role='+codigo+']').html('<span class="label label-success"> '+e+' </span>');
			botaoReceber.hide();
		})
		response.fail(function(e){
			console.log("nao ok");
		})
	});
	 
});

