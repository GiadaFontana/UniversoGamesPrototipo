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
						</td>
					</tr>`)
					.appendTo("#lista_news");
			}
		})
	}
	
	getNews()
	
		$('#lista_news').on('click', '.detail-button', function() {
		console.log('ciao')
		const id = $(this).attr('data-id')
		$('#detail-news-modal').css('display', 'block')
		getRecensione(id)
	})
	
	function getRecensione(id) {
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
	})
	
	$('#lista_news').on('click', '.delete-button', function() {
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