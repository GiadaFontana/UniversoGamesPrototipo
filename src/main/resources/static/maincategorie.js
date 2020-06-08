$(document).ready(function() {
	
	function getNews() {
		
		
		$.get("news", function(res) {
			
			for (let i = 0; i < res.length; i++) {
				$(`<tr>
						<td>${res[i].categoria}</td>
						<td>
							<button class='detail-button' data-id='${res[i].id}'>Dettaglio</button>
							<button class='delete-button' data-id='${res[i].id}'>Delete</button>
						</td>
					</tr>`)
					.appendTo("#lista_categorie");
			}
		})
	}
	
	getNews()
	
	$('#lista_categorie').on('click', '.detail-button', function() {
		
		const id = $(this).attr('data-id')
		$('#detail-categorie-modal').css('display', 'block')
		getCategoria(id)
	})
	
	function getCategoria(id) {
		$.get(`news/${id}`,function(res) {
			$(`<p><strong>Titolo: </strong>${res.titolo}</p>
			<p><strong>categoria: </strong>${res.categoria}</p>
				<p><strong>data di pubblicazione: </strong>${res.dataPubblicazione}</p>
				<p><strong>autore: </strong>${res.autore}</p>`
			).appendTo("#categoria-detail")
		})
	}
	
	$("#close-detail").on("click", function() {
		$("#detail-categorie-modal").css('display','none')
		$("#categoria-detail").html('')
	})

	
	
	$('#lista_categorie').on('click', '.delete-button', function() {
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

	
})