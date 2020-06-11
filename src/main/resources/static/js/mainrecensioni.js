$(document).ready(function() {
	
	function getRecensioni() {	
		$.get("recensioni", function(res) {
			for (let i = 0; i < res.length; i++) {
				$(`<tr>
						<td>${res[i].titoloVideogioco}</td>
						<td>
							<button class='detail-button' data-id='${res[i].id}'>Dettaglio</button>
							<button class='delete-button' data-id='${res[i].id}'>Delete</button>
							<button class='open-edit-recensioni' data-id="${res[i].id}">Modifica</button>
						</td>
					</tr>`)
					.hide()
					.appendTo("#lista-recensioni")
					.fadeIn(i*500);
			}
		})
	}
	getRecensioni() 
	
	let recensioniId= -1
	
	$('#lista-recensioni').on('click', '.detail-button', function() {

		const id = $(this).attr('data-id')
		recensioniId=id
		$('#detail-recensione-modal').css('display', 'block')
		
		getRecensione(id)
	})
	
	function getRecensione(id) {
		idRecensione=
		$.get(`recensioni/${id}`,function(res) {
			$(`<p><strong>TitoloVideogioco: </strong>${res.titoloVideogioco}</p>
				<p><strong>dataRecensione: </strong>${res.dataRecensione}</p>
				<p><strong>recensione: </strong>${res.recensione}</p>
				<p><strong>Punteggio: </strong>${res.punteggio}</p>
				<p><strong>recensore: </strong>${res.recensore}</p>`
			).appendTo("#recension-detail")
		})
	}
	
	$("#close-detail").on("click", function() {
		$("#detail-recensione-modal").hide('slow')
		$("#recension-detail").html('')
		recensioniId=-1;
	})
	$('#lista-recensioni').on('click', '.delete-button', function() {
		const id = $(this).attr('data-id')
		deleteRecensione(id, $(this).parent().parent())
	})
	function deleteRecensione(id, htmlRow) {
		$.ajax({
			url: `recensioni/${id}`,
			type: 'DELETE',
			success: function() {
				htmlRow.remove()
			}
		})
	}

	$('#open-add-recensioni').click(function () {
		$('#add-recensioni-modal').show('slow')
		
	})


    
    $("#close-add-recensioni").on("click", function() {
		$("#add-recensioni-modal").hide('slow')
	})
	
	let editMode=false;
    let editId=-1;
    
	    $('#add-recensioni').click(function() {
		const r = {
				titoloVideogioco: $('#titoloVideogioco').val(), 
				dataRecensione: $('#dataRecensione').val(),
				recensione: $('#recensione').val(), 
				punteggio: $('#punteggio').val(), 
				recensore: $('#recensore').val()
				
		}
		
		if(editMode){
			r.id=editId;
			editRecensioni(r)
			editMode = false
			idDaModificare = -1
		    $('#add-recensioni').text('Aggiungi')
			$('#add-recensioni-modal-title').text('AGGIUNGI RECENSIONE')
					
		}
		else{
			addRecensioni(r);
		}

        $('#titoloVideogioco').val('')
        $('#dataRecensione').val('')
        $('#recensione').val('')
        $('#punteggio').val('')
        $('#recensore').val('')
        $('#lista-recensioni').html('')
         getRecensioni()
        $('#add-recensioni-modal').hide('slow')
       
	   
	  })
		
		
		function addRecensioni(r){
		$.ajax({
			type: 'POST',
			url: '/recensioni',
			data: JSON.stringify(r),
			contentType: "application/json",
			dataType: 'json',
			success: function(data) {
			}
	    })
     }
	    
	    $('#lista-recensioni').on('click', '.open-edit-recensioni', function() {
			editMode = true;
			
			const id = $(this).attr('data-id')
			
			editId = id;
			
			$.get(`recensioni/${id}`, function(res) {
				$('#titoloVideogioco').val(res.titoloVideogioco)
				$('#dataRecensione').val(res.dataRecensione)
				$('#recensione').val(res.recensione)
				$('#punteggio').val(res.punteggio)
				$('#recensore').val(res.recensore)
				$('#add-recensioni').text('Modifica')
				$('#add-recensioni-modal-title').text('MODIFICA RECENSIONI')
			})
			$('#add-recensioni-modal').css('display', 'block')
		})
		
		function editRecensioni(r) {
			$.ajax({
				url: `/recensioni`,
				type:'PUT',
				data: JSON.stringify(r),
				contentType: "application/json",
				dataType: 'json',
				success: function(res) {
					
				}
			})
		}

})