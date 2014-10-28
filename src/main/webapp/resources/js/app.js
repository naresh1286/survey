function get(id){
    return document.getElementById(id);
}

var previousQuestionIndex = new Array();

function goToNext(){
    
    var count = 0;
    var total = parseInt(get('totalCount').value,10)+1;    
    count = get('qcount').value;
    
    var ansSelectIndex = getSelectedIndex(document.getElementsByName('q'+count+'o'));
    //alert(ansSelectIndex);
    if(get('nextQuestionId').value != 0 && ansSelectIndex != null){
    	ansSelectIndex = parseInt(ansSelectIndex,10)+1;
    	if(get('cquestionId'+count+ansSelectIndex).value != ''){    		
    		//alert(get('cquestionId'+count+ansSelectIndex).value);
    		get('nextQuestionId').value = get('cquestionId'+count+ansSelectIndex).value;
    	}else{
    		get('nextQuestionId').value = 0;
    	}
    }    
        
    if(count > 0 && count < total){ 
        var newQuestionIndex = 0;
        if(get('nextQuestionId').value != 0){
            newQuestionIndex = getQuestionIndex(get('nextQuestionId').value);
            //nextQuestionIndex.push(newQuestionIndex);
        }else{
            newQuestionIndex = parseInt(count,10)+1;
            //nextQuestionIndex.push(newQuestionIndex);
        }
        if(previousQuestionIndex.indexOf(count) == -1){
            previousQuestionIndex.push(count);
        }
        //alert(previousQuestionIndex);                
        if(newQuestionIndex < total && get('questionNumber').value < total){
            get('q'+count).className = 'hide';
            count = newQuestionIndex;
            get('qcount').value = count;
            get('q'+count).className = 'show';
            get('qno'+count).innerHTML = parseInt(get('questionNumber').value,10)+1;
            get('questionNumber').value = parseInt(get('questionNumber').value,10)+1;
            if(get('questionNumber').value == parseInt(total,10)-1 || newQuestionIndex == parseInt(total,10)-1){
            	//alert("disable");
            	get('nextBtn').className = 'btn btn-primary disabled';
            }
        }else{
            get('nextBtn').className = 'btn btn-primary disabled';
        }
    }else{
        get('nextBtn').className = 'btn btn-primary disabled';
    }    
    
    var qaselect = get('selectqa'+count).value;
        
    if(count == 1){
        get('prevBtn').className = 'btn btn-primary disabled';
        if(qaselect == 'yes'){
            get('nextBtn').className = 'btn btn-primary';
        }else{
            get('nextBtn').className = 'btn btn-primary disabled';
        }
    }else if(count > 1 && count < total){
        get('prevBtn').className = 'btn btn-primary';
        if(qaselect == 'yes'){        	
            get('nextBtn').className = 'btn btn-primary';
            if(get('questionNumber').value == parseInt(total,10)-1 || newQuestionIndex == parseInt(total,10)-1){            	
            	get('nextBtn').className = 'btn btn-primary disabled';
            }
        }else{
            get('nextBtn').className = 'btn btn-primary disabled';
        }
    }else if(count == total){        
        get('prevBtn').className = 'btn btn-primary';        
        get('nextBtn').className = 'btn btn-primary disabled';
    }
}

function goToPrevious(){
    
    var count = 0;
    var total = parseInt(get('totalCount').value,10)+1;    
    count = get('qcount').value;
    
    if(count > 1 && count < total){
        get('nextQuestionId').value = count;
        if(previousQuestionIndex.indexOf(count) != -1){
            previousQuestionIndex.pop(count);
        }
        //alert(previousQuestionIndex);
        get('q'+count).className = 'hide';
        if(previousQuestionIndex.length == 0){
            count = 1;
        }else{
            count = previousQuestionIndex[previousQuestionIndex.length-1];
        }
        get('qcount').value = count;
        get('q'+count).className = 'show';
        get('qno'+count).innerHTML = parseInt(get('questionNumber').value,10)-1;
        get('questionNumber').value = parseInt(get('questionNumber').value,10)-1;
    }else{    	
    	get('prevBtn').className = 'btn btn-primary disabled';    	
    }
    
    var qaselect = get('selectqa'+count).value;
    
    if(count == 1){
        get('prevBtn').className = 'btn btn-primary disabled';
        if(qaselect == 'yes'){
            get('nextBtn').className = 'btn btn-primary';
        }else{
            get('nextBtn').className = 'btn btn-primary disabled';
        }
    }else if(count > 1 && count < total){
        if(qaselect == 'yes'){
            get('prevBtn').className = 'btn btn-primary';
        }else{
            get('prevBtn').className = 'btn btn-primary disabled';
        }
        get('nextBtn').className = 'btn btn-primary';        
    }else if(count == total){
        get('prevBtn').className = 'btn btn-primary';
        get('nextBtn').className = 'btn btn-primary disabled';
    }
}

function getSelectedIndex(colRadio){
	for (var i = 0; i < colRadio.length; i++)
    {
	    if (colRadio[i].checked)
	    {
	    	return i;
	    }
    }
}

function getQuestionIndex(questionID){
    for(var i=1;i<=get('totalCount').value;i++){
        if(get('qid'+i).value == questionID){
            return i;
        }
    }
}

function makeCountZero(){
    get('qcount').value = 1;
}

function submitSurvey(){
    var total = get('totalCount').value;
    var count = 0;
    for(var i=1;i<=total;i++){
        if(get('selectqa'+i).value == 'yes'){            
            count = parseInt(count,10)+1; 
        }
    }
    if(count > 0){
        if(confirm("Are you sure that you want to submit survey?")){
            document.surveyForm.action = "/PcciSurvey/submitSurvey";
            document.surveyForm.submit();
        }
    }else{
        alert("You must attempt atleast one question!!!");
    }
}

function pxd(data, name) { return $(data).find(name).text().replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&").replace(/&raquo;/g, "Â»").replace(/&laquo;/g, "Â«"); }

function loadRelativeQuestion(rqid,cqid,qindex,aindex){
        
    var total = get('totalCount').value;
    
    if(qindex == 1){
        get('prevBtn').className = 'btn btn-primary disabled';
        get('nextBtn').className = 'btn btn-primary';
    }else if(qindex > 1 && qindex < total){
        get('prevBtn').className = 'btn btn-primary';
        get('nextBtn').className = 'btn btn-primary';
    }else if(qindex == total){
        get('prevBtn').className = 'btn btn-primary';
        get('nextBtn').className = 'btn btn-primary disabled';
    }    
    
    get('nextQuestionId').value = 0;
    
    // to enable whether user selected an option for a question or not
    get('selectqa'+qindex).value = 'yes';
    
    if(rqid != null || rqid != ''){
        var qaCount = get('qaCount'+qindex).value;
        for(var i=0;i<qaCount;i++){
            get('cq'+qindex+'a'+i).className = 'hide';
        }
        $.ajax({
            type: "POST",
            url: "/PcciSurvey/getConditionQuestion",
            data: "rqid="+rqid,
            dataType: 'json',
            success: function(data){                
                var total = data.answers.length;
                var questionId = data.questionId;
                var answers = '<br/>';
                for (var i in data.answers) {
                    answers += '<div class="checkbox"><label><input type="radio" id="q'+qindex+'a'+aindex+'r" name="q'+qindex+'a'+aindex+'r" value="'+data.answers[i].answerId+'" />'+data.answers[i].answerText+'</label></div>';                    
                }                                
                get('cq'+qindex+'a'+aindex).innerHTML = '<div class="col-md-offset-1">'+data.questionText + answers + '</div>';
                get('cq'+qindex+'a'+aindex).className = 'show';
            },
            error: function (data){
                //alert("Please try again");
            }
        });    
        if(cqid != ''){
            //nextQuestionIndex.push(getQuestionIndex(cqid));
            get('nextQuestionId').value = cqid;
        }
    }else{
        var qaCount = get('qaCount'+qindex).value;
        for(var i=0;i<qaCount;i++){
            get('cq'+qindex+'a'+i).className = 'hide';
        }
        if(cqid != ''){
            //nextQuestionIndex.push(getQuestionIndex(cqid));
            get('nextQuestionId').value = cqid;
        }        
    }
    
    var done = document.getElementsByName('q'+qindex+'o');
    
    for(var i=parseInt(qindex,10)+1;i<=total;i++){    	
    	var options = document.getElementsByName('q'+i+'o');
    	get('selectqa'+i).value = 'no';
    	for(var j=0;j<options.length;j++){
    		if(options[j].getAttribute("type") == 'radio'){
    			get('cq'+i+'a'+j).className = 'hide';
    			options[j].checked = false;
    		}
    	}
    }
            
}