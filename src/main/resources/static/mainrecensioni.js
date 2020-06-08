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
					.appendTo("#lista_recensioni")
					
			}
		})
	}
	
	getRecensioni() 
	
	
	$('#lista_recensioni').on('click', '.detail-button', function() {
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
	$('#lista_recensioni').on('click', '.delete-button', function() {
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

})
