$(function () {
	
	var table = $('<table>').addClass('foo').attr("id", "the_table");
	for(i=0; i<8; i++){
		var row = $('<tr>').addClass('bar');
		table.append(row);
		for(j=0; j<25; j++){
			if(i==0){
			var cell = $('<td>').addClass('td').text(j-1);
			}
			else{
			var cell = $('<td>').addClass('td');
			}
			table.append(cell);
		}
	}
	
	$("body").append(table);
	
	
	
	  var isMouseDown = false,
		isHighlighted;
	  $("#our_table td")
		.mousedown(function () {
		  isMouseDown = true;
		  $(this).toggleClass("highlighted");
		  isHighlighted = $(this).hasClass("highlighted");
		  return false; // prevent text selection
		})
		.mouseover(function () {
		  if (isMouseDown) {
			$(this).toggleClass("highlighted", isHighlighted);
		  }
		})
		.bind("selectstart", function () {
		  return false;
		})

	  $(document)
		.mouseup(function () {
		  isMouseDown = false;
		});
		
			  $("#the_table td")
		.mousedown(function () {
		  isMouseDown = true;
		  $(this).toggleClass("highlighted");
		  isHighlighted = $(this).hasClass("highlighted");
		  return false; // prevent text selection
		})
		.mouseover(function () {
		  if (isMouseDown) {
			$(this).toggleClass("highlighted", isHighlighted);
		  }
		})
		.bind("selectstart", function () {
		  return false;
		})

	  $(document)
		.mouseup(function () {
		  isMouseDown = false;
		});
});