$(document).ready(function(){

	let modal = $('.modal');
	let modalContent = $('.modal-content');
	
	$('#lista-news').on('click', '.detail', function(){
		const id = +$(this).attr('data-id');
		getNewss(id);
		$('#my-modal').show('slow');
	})
	
	
	$('#close-detail').click(function(){
		$('#titolo-news').html('<b>Titolo: </b>');
		$('#categoria-news').html('<b>Categoria: </b>');
		$('#contenuto-news').html('<b>Contenuto: </b>');
		$('#dataPubblicazione-news').html('<b>Data di Pubblicazione: </b>');
		$('#autore-news').html('<b>autore: </b>');
		$('#my-modal').hide('slow');
		$(this).parent().find('button').remove();
	})
	
	$('.add-news-btn').click(function(){
		let lastID = 0;
		$.get('news', function(res){
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
		$('input[NAME=\'titolo\']').val('');
		$('input[NAME=\'categoria\']').val('');
		$('input[NAME=\'contenuto\']').val('');
		$('input[NAME=\'dataPubblicazione\']').val('');
		$('input[NAME=\'autore\']').val('');
		
	})
	
	
	
	function getNews(){
		$.get("news", function(res){
			for(let i = 0; i < res.length; i++) {
				$(`<tr data-id=${res[i].id}>
						<td>${res[i].titolo}</td>
						<td>
							<button class='detail' data-id='${res[i].id}'>Dettaglio</button>
						</td>
					</tr>`)
					.appendTo("#lista-news");
				
			}
		})
	}
	
	getNews();
	
	function getNewss(id){
		$.get(`news/${id}`, function(res){
			let id = res.id
			let titolo = res.titolo;
			let categoria = res.categoria;
			let contenuto = res.contenuto;
			let dataPubblicazione = res.dataPubblicazione;
			let autore = res.autore;
			
			
			
			$('#titolo-news').append(titolo);
			$('#categoria-news').append(categoria);
			$('#contenuto-news').append(contenuto);
			$('#dataPubblicazione-news').append(dataPubblicazione);
			$('#autore-news').append(autore);
			$('#detail-content').append(`<button class='edit-news' data-id='${id}'>Modifica</button> 
										<button class='delete-news' data-id='${id}'>Elimina</button>`)
		})
	}
	
	
	$('.news-add-confirm').click(function(){
		const n = {
				titolo: $('input[NAME=\'titolo\']').val(),
				categoria: $('input[NAME=\'categoria\']').val(),
				contenuto: $('input[NAME=\'contenuto\']').val(),
				dataPubblicazione: $('input[NAME=\'dataPubblicazione\']').val(),
				autore: $('input[NAME=\'autore\']').val(),
		}
		
		if (editMode){
			n.id=editId;
			editRecensione(n);
			
		}  
		else {
			addNews(n);
		}
		$('input[NAME=\'titolo\']').val('');
		$('input[NAME=\'categoria\']').val('');
		$('input[NAME=\'contenuto\']').val('');
		$('input[NAME=\'dataPubblicazione\']').val('');
		$('input[NAME=\'autore\']').val('');
	
		
	})
	
	function addNews(news){
		$.ajax({
			type: 'POST',
			url: '/news',
			data: JSON.stringify(news),
			contentType: "application/json",
			dataType: 'json',
			success: function(res) {
				$('#lista-news').html('');
				getNews();
			}
	    })
     }
	
	
	function editNews(news){
		$.ajax({
			url: `/news`,
			type: 'PUT',
			data: JSON.stringify(news),
			success: function(res){
				editMode = false; //per questo e idDaModificare quardare sotto sul click .edit-student
				idDaModificare = -1;
				$('#lista-news').html('');
				getNews();
				$('#my-modal-add').hide('slow');
				$('#titolo-news').html('<b>Titolo : </b>');
				$('#categoria-news').html('<b>categoria: </b>');
				$('#contenuto-news').html('<b>Contenuto: </b>');
				$('#dataPubblicazione-news').html('<b>DataPubblicazione</b>');
				$('#autore-news').html('<b>autore</b>');
				
				$('#my-modal').hide();
				$('#close-detail').parent().find('button').remove();
				$('#my-modal-add').hide('slow');	
				
			}
		})
	}

	function deleteNews(id){
		$.ajax({
			url: `news/${id}`,
			type: 'DELETE',
			success: function(){
					
					$('#lista-news').html('');
					getNews();
					
					
				}
			})
		}	
	
	$('#detail-content').on('click', '.delete-news', function(){
		const id = +$(this).attr('data-id');
		deleteNews(id)
		$('#detail-content').hide('slow');
		
	
	})
	
let editMode = false;
	let idDaModificare = -1;

	$('#detail-content').on('click', '.edit-news', function(){
		let id = +$(this).attr('data-id');
		editMode = true;
	    editId=id;
		$.get(`news/${id}`, function(res) {
			$('#titolo').val(res.titolo)
			$('#categoria').val(res.categoria)
			$('#contenuto').val(res.contenuto)
			$('#dataPubblicazione').val(res.dataPubblicazione)
			$('#autore').val(res.autore)
			
		})
		$('#my-modal-add').show('slow');
			
	})
	
})