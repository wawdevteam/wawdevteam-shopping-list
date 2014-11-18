import java.text.Normalizer

def normalize(String path){
	Normalizer.normalize(path, Normalizer.Form.NFD).replaceAll(/[^A-z0-9 ]/, "").replaceAll(/ +/, "_")
}

if (args.length == 0){
	println 'No filename provided as command line argument'
	System.exit 1
}

def productsCategoriesFileName = args[0]

File inputFile = new File(productsCategoriesFileName)

if(inputFile.exists()){

	def lineSeparator = System.getProperty('line.separator')
	
	def fileNameTokenized = productsCategoriesFileName.tokenize(".")
	
	def fileNameWithoutExtension = fileNameTokenized[0] + '.' + fileNameTokenized[1];
	
	def langCode = fileNameTokenized = fileNameTokenized[1]
	
	File outputFile = new File(fileNameWithoutExtension + ".js")
	
	outputFile << '//mongeez formatted javascript' + lineSeparator
	outputFile << '//changeset akluz:Categories_initial_import_' + langCode + lineSeparator
	
	inputFile = new File(productsCategoriesFileName).eachLine { line ->

		if(!line.startsWith("#")){
			def categoryPath = line.tokenize("->")

			//		def categoryCode = normalize(categoryPath.join("")).toLowerCase()

			def categoryName = categoryPath.last().trim()

			categoryPath.remove(categoryPath.last())

			def json = new groovy.json.JsonBuilder()
			json {
				//			code categoryCode
				name categoryName
				lang langCode 
				path categoryPath.collect { element -> element.trim() }.join(">>")
			}

			outputFile << 'db.category.insert('+json.toString()+');'+lineSeparator
		}
	}
}else{
	println 'File ' + inputFile.absolutePath + 'doesn\'t exists'
}


