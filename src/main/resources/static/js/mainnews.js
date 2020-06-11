$(document).ready(function() {
	
	function getNews() {
		
	
		$.get("news", function(res)
				
				{
			  console.log(res);
			for (let i = 0; i < res.length; i++) {
				$(`<tr>
						<td>${res[i].titolo}</td>
						<td>
							<button class='detail-button' data-id='${res[i].id}'>Dettaglio</button>
							<button class='delete-button' data-id='${res[i].id}'>Delete</button>
							<button class="open-edit-news" data-id="${res[i].id}">Modifica</button>
						</td>

						</td>
					</tr>`)
					.hide()
					.appendTo("#lista-news")
				    .fadeIn(i*500);
			}
		})
	}
	
	getNews()
	
	let newsId= -1
	
		$('#lista-news').on('click', '.detail-button', function() {
		const id = $(this).attr('data-id')
		newsId=id
		$('#detail-news-modal').css('display', 'block')
		getNewss(id)
	})
	
	function getNewss(id){
		idNews=
		$.get(`news/${id}`,function(res) {
			$(`<p><strong>Titolo: </strong>${res.titolo}</p>
			<p><strong>categoria: </strong>${res.categoria}</p>
				<p><strong>data di pubblicazione: </strong>${res.dataPubblicazione}</p>
				<p><strong>autore: </strong>${res.autore}</p>`
					
					
			).appendTo("#new-detail")
		})
		
	}
	
	$("#close-detail").on("click", function() {
		$("#detail-news-modal").css('display','none')
		$("#new-detail").html('')
		newsId=-1;
	})
	
	$('#lista-news').on('click', '.delete-button', function() {
		const id = $(this).attr('data-id')
		deleteNews(id, $(this).parent().parent())
	})
	
	function deleteNews(id, htmlRow) {
		$.ajax({
			url: `news/${id}`,
			type: 'DELETE',
			success: function() {
				htmlRow.remove()
			}
		})
	}

	$('#open-add-news').click(function () {
		$('#add-news-modal').show('slow')
		
	})

	const categorie = [];
	function getCategorie() {
        $.get('news', function (res) {
            for (let i = 0; i < res.length; i++) {
            categorie.push(res[i].categoria)
            }
        })
    }
	

    const categorieCensite = []
	function censisciCategorie(){
        categorieCensite.splice(0, categorieCensite.length);
        for (const categoria of categorie){
            let esiste = false; 
            if (categorieCensite.length == 0){
                categorieCensite.push(categoria);
            } else {
                for (const categoria2 of categorieCensite){
                    if (categoria === categoria2){ 
                        esiste = true;
                        break
                     }
                } if (!esiste){
                    categorieCensite.push(categoria);
                }    
            }
        }
        console.log(categorieCensite);
        
	}
    
    $("#close-add-news").on("click", function() {
		$("#add-news-modal").hide('slow')
	})
	 
	let editMode=false;
    let editId=-1;
    
	    $('#add-news').click(function() {
		const n = {
				titolo: $('#titolo').val(), 
				categoria: $('#categoria').val(),
				contenuto: $('#contenuto').val(), 
				datapubblicazione: $('#dataPubblicazione').val(), 
				autore: $('#autore').val()
				
		}
		if(editMode){
			n.id=editId;
			editNews(n)
			editMode = false
			idDaModificare = -1
		    $('#add-news').text('Aggiungi')
			$('#add-news-modal-title').text('AGGIUNGI NEWS')
			
		}
		else{
			addNews(n);
		}
		

        $('#titolo').val('')
        $('#categoria').val('')
        $('#contenuto').val('')
        $('#dataPubblicazione').val('')
        $('#autore').val('')
        $('#lista-news').html('')
         getNews()
        $('#add-news-modal').hide('slow')
       
	   
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
	    $('#lista-news').on('click', '.open-edit-news', function() {
			editMode = true;
			
			const id = $(this).attr('data-id')
			
			editId = id;
			
			$.get(`news/${id}`, function(res) {
				$('#titolo').val(res.titolo)
				$('#categoria').val(res.categoria)
				$('#contenuto').val(res.contenuto)
				$('#dataPubblicazione').val(res.dataPubblicazione)
				$('#autore').val(res.autore)
				$('#add-news').text('Modifica')
				$('#add-news-modal-title').text('MODIFICA NEWS')
			})
			$('#add-news-modal').css('display', 'block')
		})
		
		function editNews(n) {
	    	
			$.ajax({
				url: `/news`,
				type:'PUT',
				data: JSON.stringify(n),
				contentType: "application/json",
			    dataType: 'json',
				success: function(res) {
					
				
			    }
			})
		}
	    
})
 
    
    

