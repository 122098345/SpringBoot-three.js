/**
 * @author mrdoob / http://mrdoob.com/
 */

Menubar.File = function (editor) {

	var NUMBER_PRECISION = 6;

	function parseNumber(key, value) {

		return typeof value === 'number' ? parseFloat(value.toFixed(NUMBER_PRECISION)) : value;

	}

	//

	var config = editor.config;
	var strings = editor.strings;

	var container = new UI.Panel();
	container.setClass('menu');

	var title = new UI.Panel();
	title.setClass('title');
	title.setTextContent(strings.getKey('menubar/file'));
	container.add(title);

	var options = new UI.Panel();
	options.setClass('options');
	container.add(options);

	// New

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/new'));
	option.onClick(function () {

		if (confirm('任何未保存的数据都将丢失，确认吗？')) {

			editor.clear();

		}

	});
	options.add(option);

	//

	options.add(new UI.HorizontalRule());

	// Import

	var form = document.createElement('form');
	form.style.display = 'none';
	document.body.appendChild(form);

	var fileInput = document.createElement('input');
	fileInput.multiple = true;
	fileInput.type = 'file';
	fileInput.addEventListener('change', function (event) {

		editor.loader.loadFiles(fileInput.files);
		form.reset();

	});
	form.appendChild(fileInput);

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/import'));
	option.onClick(function () {

		fileInput.click();

	});
	options.add(option);

	//

	options.add(new UI.HorizontalRule());

	// Export Geometry

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/geometry'));
	option.onClick(function () {

		var object = editor.selected;

		if (object === null) {

			alert('No object selected.');
			return;

		}

		var geometry = object.geometry;

		if (geometry === undefined) {

			alert('The selected object doesn\'t have geometry.');
			return;

		}

		var output = geometry.toJSON();

		try {

			output = JSON.stringify(output, parseNumber, '\t');
			output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');

		} catch (e) {

			output = JSON.stringify(output);

		}

		saveString(output, 'geometry.json');

	});
	options.add(option);

	// Export Object

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/object'));
	option.onClick(function () {

		var object = editor.selected;

		if (object === null) {

			alert('No object selected');
			return;

		}

		var output = object.toJSON();

		try {

			output = JSON.stringify(output, parseNumber, '\t');
			output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');

		} catch (e) {

			output = JSON.stringify(output);

		}

		saveString(output, 'model.json');

	});
	options.add(option);

	// Export Scene

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/scene'));
	option.onClick(function () {

		var output = editor.scene.toJSON();

		try {

			output = JSON.stringify(output, parseNumber, '\t');
			output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');

		} catch (e) {

			output = JSON.stringify(output);

		}

		saveString(output, 'scene.json');

	});
	options.add(option);

	// 横线

	options.add(new UI.HorizontalRule());

	// Export DAE

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/dae'));
	option.onClick(function () {

		var exporter = new THREE.ColladaExporter();

		exporter.parse(editor.scene, function (result) {

			saveString(result.data, 'scene.dae');

		});

	});
	options.add(option);

	// Export STL (ASCII)

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/export/stl'));
	option.onClick(function () {

		var exporter = new THREE.STLExporter();

		saveString(exporter.parse(editor.scene), 'model.stl');

	});
	options.add(option);

	// Export STL (Binary)


	// 横线

	options.add(new UI.HorizontalRule());

	// Publish

	var option = new UI.Row();
	option.setClass('option');
	option.setTextContent(strings.getKey('menubar/file/publish'));
	option.onClick(function () {
		var output = editor.scene.toJSON();

		output = JSON.stringify(output, parseNumber, '\t');
		output = output.replace(/[\n\t]+([\d\.e\-\[\]]+)/g, '$1');

		//传至后台
		$.ajax({
			type: 'post',
			url: '/getModel',
			data: output,
			contentType: "application/json;"
		});
		window.location = 'xjmx';
		editor.clear();
	});
	options.add(option);

	//

	var link = document.createElement('a');
	link.style.display = 'none';
	document.body.appendChild(link); // Firefox workaround, see #6594

	function save(blob, filename) {

		link.href = URL.createObjectURL(blob);
		link.download = filename || 'data.json';
		link.click();

		// URL.revokeObjectURL( url ); breaks Firefox...

	}

	function saveArrayBuffer(buffer, filename) {

		save(new Blob([buffer], { type: 'application/octet-stream' }), filename);

	}

	function saveString(text, filename) {

		save(new Blob([text], { type: 'text/plain' }), filename);

	}

	return container;

};
