

/**
 
 JPEG File [JpegFile [object File] Baseline DCT 4032x3024 Depth: 8]
[0]	General 
	[0]: Depth: 8
	[1]: Pixel Height: 3024
	[2]: Pixel Width: 4032
	[3]: Type: Baseline DCT
[1] Exif
	[0]: Aperture Value: 1.53
	[1]: Brightness: 7.48
	[2]: Color Space: 1
	[3]: Date Time Digitized: 2016:11:19 16:08:09
	[4]: Date Time Original: 2016:11:19 16:08:09
	[5]: DateTime subseconds: 0970
	[6]: DateTimeDigitized subseconds: 0970
	[7]: DateTimeOriginal subseconds: 0970
	[8]: Exif Version: undefined
	[9]: Exposure Bias Value: 0
	[10]: Exposure Mode: 0
	[11]: Exposure program: 2
	[12]: Exposure time: 1 / 1204
	[13]: FNumber: 1.7
	[14]: Flash: 0
	[15]: FlashPix Version: undefined
	[16]: Focal Length: 4.2
	[17]: Focal length in 35 mm film: 26
	[18]: ISO Speed Ratings: 50
	[19]: Interoperability tag: 828
	[20]: Manufacturer notes: undefined
	[21]: Max Aperture Value: 1.53
	[22]: Metering Mode: 5
	[23]: Pixel X Dimension: 4032
	[24]: Pixel Y Dimension: 3024
	[25]: Scene Capture Type: 0
	[26]: Shutter Speed: 10.23
	[27]: Unique image ID: 
	[28]: User comments: undefined
	[29]: White Balance: 0
[2] TIFF
	[0]: Date and time: 2016:11:19 16:08:09
	[1]: Exif tag: 202
	[2]: Make: samsung
	[3]: Model: SM-G935S
	[4]: Orientation of image: 6
	[5]: Resolution Unit: 2
	[6]: Software: G935SKSU1APG7
	[7]: X Resolution: 72
	[8]: Y Resolution: 72
	[9]: Y and C positioning: 1
**/

    /* Imports */
    var $j = this.JpegMeta.JpegFile;


    function strComp(a, b) {
	return (a > b) ? 1 : (a == b) ? 0 : -1;
    }


	function display(data, filename) {
	    var jpeg = new $j(data, filename);
	    var groups = new Array;
	    var props;
	    var group;
	    var prop;
        var propsList= {};
	    
	    if (jpeg.gps && jpeg.gps.longitude) {
		//$("status").innerHTML += "<a href='http://maps.google.com/?q=" + jpeg.gps.latitude + "," + jpeg.gps.longitude + "&amp;spn=0.05,0.05&amp;t=h&amp;om=1&amp;hl=en' target='_blank'>Locate on map</a> (opens a new window) <br />";
	    }

	    for (group in jpeg.metaGroups) {
                if (jpeg.metaGroups.hasOwnProperty(group)) {
                		groups.push(jpeg.metaGroups[group]);
                }
	    }
	    
	    groups.sort(function (a, b) {
		if (a.description == "General") {
		    return -1;
		} else if (b.description == "General") {
		    return 1;
		} else {
		    return strComp(a.description, b.description);
		}
	    });

	
	    
	    for (var i = 0; i < groups.length; i++) {
            group = groups[i];
			props = new Array();
			for (prop in group.metaProps) {
                if (group.metaProps.hasOwnProperty(prop)) {
                	props.push(group.metaProps[prop]);
                }
			}
			props.sort(function (a, b) { return strComp(a.description, b.description); });
			for (var j = 0; j < props.length; j++) {
		               prop = props[j];
		        
	                   propsList[prop.description.replace(/ |\./gi, '')] =  prop.value;
	                
	                  console.log(prop.description);
	                  console.log(prop.value);
		          // $("status").innerHTML += "<em>" + prop.description + ":</em> " + prop.value + "<br />";
				}
	    	}
	    
	    return propsList;
	    //return groups;
	}

	

	function getProp(groups){
	
		    var props1 = {};
            var group = groups[i];
			var props = new Array();
		    var prop;
		    
			for (prop in group.metaProps) {
	            if (group.metaProps.hasOwnProperty(prop)) {
			        props.push(group.metaProps[prop]);
			       // alert(group.metaProps[prop]);
	                }
			}
			props.sort(function (a, b) { return strComp(a.description, b.description); });
			return props[j];
		
	} 
	
	
	/*
	function getProp(groups,i,j){
	
		    var props1 = {};
            var group = groups[i];
			var props = new Array();
		    var prop;
		    
			for (prop in group.metaProps) {
	            if (group.metaProps.hasOwnProperty(prop)) {
			        props.push(group.metaProps[prop]);
			       // alert(group.metaProps[prop]);
	                }
			}
			props.sort(function (a, b) { return strComp(a.description, b.description); });
			return props[j];
		
	} */
	
	
	



