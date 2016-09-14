#Ti.FilePicker

This module written by Jorge and Rainer is an Titanium Android module for picking files from device.


##Interface


```javascript
var FilePicker = require("ti.filepicker");
FilePicker.showAllFiles({
  mimeTypes: ["*/pdf","*/magicstuff"],
  onSuccess : function(_e) {
      _e.list.foreach(function(uri){
         FilePicker.getFile(uri);
      })
  }
  
})

```

<img src="http://i.imgur.com/rvY4vrr.png" width=300 />
<img src="http://i.imgur.com/ShCq3NW.png" width=300 />
