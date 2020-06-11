$(document).ready(function() {
	
	function getRecensioni() {	
		$.get("recensioni", function(res) {
			for (let i = 0; i < res.length; i++) {
				$(`<tr>
						<td>${res[i].titoloVideogioco}</td>
						<td>
							<button class='detail-button' data-id='${res[i].id}'>Dettaglio</button>
							<button class='delete-button' data-id='${res[i].id}'>Delete</button>
						</td>
					</tr>`)
					.appendTo("#lista-recensioni")
			}
		})
	}
	getRecensioni() 
	$('#lista-recensioni').on('click', '.detail-button', function() {
		console.log('ciao')
		const id = $(this).attr('data-id')
		$('#detail-recensione-modal').css('display', 'block')
		getRecensione(id)
	})
	
	function getRecensione(id) {
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
		$("#detail-recensione-modal").css('display','none')
		$("#recension-detail").html('')
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
		console.log('boh')
		$('#add-recensioni-modal').show('slow')
		
	})


    
    $("#close-add-recensioni").on("click", function() {
		$("#add-recensioni-modal").hide('slow')
	})
    
	    $('#add-recensioni').click(function() {
		const r = {
				titoloVideogioco: $('#titoloVideogioco').val(), 
				dataRecensione: $('#dataRecensione').val(),
				recensione: $('#recensione').val(), 
				punteggio: $('#punteggio').val(), 
				recensore: $('#recensore').val()
				
		}
		
		addRecensioni(r)

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

})