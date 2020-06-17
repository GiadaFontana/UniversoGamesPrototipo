$(document).ready(function(){

	let modal = $('.modal');
	let modalContent = $('.modal-content');
	
	$('#lista-recensioni').on('click', '.detail', function(){
		const id = +$(this).attr('data-id');
	     getRecensione(id);
		$('#my-modal').show('slow');
	})
	
	$('#close-detail').click(function(){
		$('input[NAME=\'titoloVideogioco\']').html('<b> titolo :</b>');
		$('input[NAME=\'dataRecensione\']').html('<b> dataRecensione :</b>');;
		$('input[NAME=\'recensione\']').html('<b> recensione :</b>');
		$('input[NAME=\'punteggio\']').html('<b> punteggio:</b>');
		$('input[NAME=\'recensore\']').html('<b> Recensore:</b>');
		$('#my-modal').hide('slow');
		$(this).parent().find('button').remove();
	})
	
	$('.add-recensione-btn').click(function(){
		let lastID = 0;
		$.get('recensioni', function(res){
			for(let i = 0; i < res.length; i++){
				lastID = res[i].id;
				
			}
			lastID = lastID + 1;
			
			$('#id-for-edit').append(lastID);
		})
		$('#my-modal-add').show('slow');
	})
	
	$('#close-add-form').click(function(){
		$('#my-modal-add').hide('slow');
		$('#id-for-edit').html('<b>ID: </b>');
	})
		
	$('.abort').click(function(){
		$('#my-modal-add').hide('slow');
		$('#id-for-edit').html('<b>ID: </b>');
		$('input[NAME=\'titoloVideogioco\']').val('');
		$('input[NAME=\'dataRecensione\']').val('');
		$('input[NAME=\'recensione\']').val('');
		$('input[NAME=\'punteggio\']').val('');
		$('input[NAME=\'recensore\']').val('');
		
	})
	
	

	function getRecensioni(){
		$.get("recensioni", function(res){
			for(let i = 0; i < res.length; i++) {
//				$(`<tr data-id=${res[i].id}>
//						<td>${res[i].titoloVideogioco}</td>
//						<td>
//							<button class='detail' data-id='${res[i].id}'>Dettaglio</button>
//						</td>
//					</tr>`)
//					.appendTo("#lista-recensioni");
				$(`<tr class='riga-${res[i].id}'>
						<td>${res[i].titoloVideogioco}</td>
						<td>
							<button class='detail' data-id='${res[i].id}'>Dettaglio</button>
						</td>
					</tr>`)
					.appendTo("#lista-recensioni");
				
			}
		})
	}
	
	getRecensioni();
	
	function getRecensione(id){
		$.get(`recensioni/${id}`, function(res){
			let titoloVideogioco = res.titoloVideogioco;
			let dataRecensione = res.dataRecensione;
			let recensione = res.recensione;
			let punteggio = res.punteggio;
			let recensore = res.recensore;
			
			$('#titoloVideogioco-recensione').append(titoloVideogioco);
			$('#dataRecensione-recensione').append(dataRecensione);
			$('#recensione-recensione').append(recensione);
			$('#punteggio-recensione').append(punteggio);
			$('#recensore-recensione').append(recensore);
			$('#detail-content').append(`<button class='edit-recensione' data-id='${id}'>Modifica</button> 
										<button class='delete-recensione' data-id='${id}'>Elimina</button>`)
		})
		$.get(`commenti-recensione/${id}`, function(res){
			let listaCommenti = ``;
			
			for(const commento of res){
				console.log(res);
				listaCommenti += `<tr>
								  <td>${commento.idUtente}</td>
								  <td>${commento.commento}</td>
								  </tr>`;
			}
			
			$('#tabella-commenti').append(listaCommenti);
			
		})
	}
	
	
	
	$('.recensione-add-confirm').click(function(){
		const r = {
				titoloVideogioco:$('input[NAME=\'titoloVideogioco\']').val(),
				dataRecensione: $('input[NAME=\'dataRecensione\']').val(),
				recensione: $('input[NAME=\'recensione\']').val(),
				punteggio: $('input[NAME=\'punteggio\']').val(),
				recensore : $('input[NAME=\'recensore\']').val()
		}
		
		if (editMode){
			r.id = idDaModificare; 
			editRecensione(r);
		} else {
			addRecensione(r);
		}
		$('input[NAME=\'titoloVideogioco\']').val('');
		$('input[NAME=\'dataRecensione\']').val('');
		$('input[NAME=\'recensione\']').val('');
		$('input[NAME=\'punteggio\']').val('');
		$('input[NAME=\'recensore\']').val('');
		
		
	})
	
	function addRecensione(recensione){
		$.ajax({
			type: 'POST',
			url: '/recensioni',
			data: JSON.stringify(recensione),
			contentType: "application/json",
			dataType: 'json',
			success: function(res) {
				$('#lista-recensioni').html('');
				getRecensioni();
			}
	    })
     }
			
	

	
	function editRecensione(recensione){
		$.ajax({
			url: `/recensioni`,
			type: 'PUT',
			data: JSON.stringify(recensione),
			contentType: "application/json",
			dataType: 'json',
			success: function(res){
				
					editMode = false; 
					idDaModificare = -1;
					$('#lista-recensioni').html('');
					getRecensioni();
					$('#my-modal-add').hide('slow');
					$('#titoloVideogioco-recensione').html('<b>Titolo Videogioco: </b>');
					$('#dataRecensione-recensione').html('<b>DataRecensione: </b>');
					$('#recensione-recensione').html('<b>Recensione: </b>');
					$('#punteggio-recensione').html('<b>Punteggio </b>');
					$('#recensore-recensione').html('<b>Recensore</b>');
					
					$('#my-modal').hide();
					$('#close-detail').parent().find('button').remove();
					$('#my-modal-add').hide('slow');
				
			}
		})
	}

	function deleteRecensione(id){
		$.ajax({
			url: `recensioni/${id}`,
			type: 'DELETE',
			success: function(){
					$('#lista-recensioni').html('');
					getRecensioni();
					
				}
		})
	}
	
	$('#detail-content').on('click', '.delete-recensione', function(){
		const id = +$(this).attr('data-id');
		deleteRecensione(id, $(`tr[class='riga-${id}']`));
		$('#detail-content').hide('slow');
//		
	})
	


	let editMode = false;
	let idDaModificare = -1;
	$('#detail-content').on('click', '.edit-recensione', function(){
		let id = +$(this).attr('data-id');
		editMode = true;
		editId= id;
		$.get(`recensioni/${id}`, function(res){
			$('#titoloVideogioco').val(res.titoloVideogioco);
			$('#dataRecensione').val(res.dataRecensione);
			$('#recensione').val(res.recensione);
			$('#punteggio').val(res.punteggio);
			$('#recensore').val(res.recensore);
		
		})
		$('#my-modal-add').show('slow');		
	})
	
})