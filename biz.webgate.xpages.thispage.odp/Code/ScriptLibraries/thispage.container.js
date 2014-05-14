/*
 * © Copyright WebGate Consulting AG, 2014
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

dojo.provide("thispage.container");
dojo.require("dijit._Widget");
dojo.require("dojox.dtl._Templated");
dojo.declare("thispage.container", [ dijit._Widget,
		dojox.dtl._Templated ], {
	containerid : null,
	feed : null,
	templateString : "",
	targetid : null,
	pageid:null,
	postCreate : function() {
		//alert(this.templateString);
		var mySelf = this;
		var xhrArgs = {
			url : "xsp/this.page/db/feed.json?id="+mySelf.containerid+"&pageid="+mySelf.pageid,
			handleAs : "json",
			headers : {
				"Content-Type" : "application/json"
			},
			preventCache : true,
			load : function(data) {
				if (data.status == "ok") {
					mySelf.feed = data;
					mySelf.render();
				} else {
					alert(data.error);
				}
			},
			error : function(error) {
				alert(error);
			}

		}
		var deferred = dojo.xhrGet(xhrArgs);
	}
});

// -- custom DTL filter to parse datetime objects in Internuts Explorer
// -- the built-in date tag of DTL will not work with ISO timestamps
dojo.provide('thispage.dtl.filter.datetime');
dojo.require("dojo.date.locale");

dojo.mixin(thispage.dtl.filter.datetime, {
	thispageDateFilter : function(value) {
		var dt = dojo.date.stamp.fromISOString(value);
		return dojo.date.locale.format(dt, {
			selector : "date",
			formatLength : "medium"
		});
	},

	thispageTimeFilter : function(value) {
		var dt = dojo.date.stamp.fromISOString(value);
		return dojo.date.locale.format(dt, {
			selector : "time",
			formatLength : "medium"
		});
	}

});

dojox.dtl.register.filters('thispage.dtl.filter', {
	datetime : [ 'thispageDateFilter', 'thispageTimeFilter' ]
});
// -- end of custom DTL filter
