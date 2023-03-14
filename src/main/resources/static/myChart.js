var chartDataStr = decodeHtml(chartData);

// converts string object into json object
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(var i=0; i < arrayLength; i++){
    numericData[i] = chartJsonArray[i].count;
    labelData[i] = chartJsonArray[i].label;
    // alert(labelData + ":" + numericData);
}

// For a pie chart
const ctx = document.getElementById('myPieChart');

new Chart(ctx, {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8c5ea2", "#3cba9f"],
            borderColor: 'rgb(255, 99, 132)',
            data: numericData
        }]
    },

    // Configuratiton option go here
    options: {
        title: {
            display: true,
            text: "Project Status"
        }
    }
});

// "[{"value": 1, "label": "COMPLETED"}, {"value": 2, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html){
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

