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
		$('#datapubblicazione-news').html('<b>Data di Pubblicazione: </b>');
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
		$('input[NAME=\'datapubblicazione\']').val('');
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
			
			
			$('#id-news').append(id);
			$('#titolo-news').append(titolo);
			$('#categoria-news').append(categoria);
			$('#contenuto-news').append(contenuto);
			$('#dataPubblicazione-news').append(dataPubblicazione);
			$('#autore-news').append(autore);
			$('#detail-content').append(`<button class='edit-news' data-id='${id}'>Modifica</button> 
										<button class='delete-news' data-id='${id}'>Elimina</button>`)
		})
	}
	
	let editMode=false;
    let editId=-1;
	$('.news-add-confirm').click(function(){
		const n = {
				titolo: $('input[NAME=\'titolo\']').val(),
				categoria: $('input[NAME=\'categoria\']').val(),
				contenuto: $('input[NAME=\'contenuto\']').val(),
				dataPubblicazione: $('input[NAME=\'datapubblicazione\']').val(),
				classe: $('input[NAME=\'autore\']').val(),
		}
		
		if (editMode){
			n.id=editId;
			editNews(n)
			editMode = false
			idDaModificare = -1
		}  
		else {
			addNews(n);
		}
		
		$('#titolo').val('')
        $('#categoria').val('')
        $('#contenuto').val('')
        $('#dataPubblicazione').val('')
        $('#autore').val('')
        $('#lista-news').html('')
         getNews()
         $('#my-modal-add').hide('slow')
       
		
	})
	
	function addNews(n){
		$.ajax({
			type: 'POST',
			url: '/news',
			data: JSON.stringify(n),
			contentType: "application/json",
			dataType: 'json',
			success: function(data) {
			}
	    })
     }
	
	
	function editNews(n){
		$.ajax({
			url: `/news`,
			type: 'PUT',
			data: JSON.stringify(n),
			success: function(res){
					
				
			}
		})
	}

	function deleteNews(id, rowHtml){
		$.ajax({
			url: `news/${id}`,
			type: 'DELETE',
			success: function(){
					rowHtml.remove();
					
				}
			})
		}	
	
	$('#detail-content').on('click', '.delete-news', function(){
		const id = +$(this).attr('data-id');
		deleteNews(id, $(this).parent().parent())
	
	})
	


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
		$('#my-modal-add').css('display', 'block');
			
	})
	
})