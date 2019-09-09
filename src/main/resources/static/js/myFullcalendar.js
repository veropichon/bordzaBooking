document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid', 'timeGrid', 'list', 'interaction'],
        defaultView: 'timeGridWeek',
        hiddenDays: [ 0 ],
        allDaySlot: false,
        locale: 'fr',
        minTime: "09:00:00",
        maxTime: "20:00:00",
        nowIndicator: true,
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
        },
        buttonText: {
            today: 'Aujourd\'hui',
            month: 'Mois',
            week : 'Semaine',
            day  : 'Jour',
            list : 'Liste',
        },
        eventSources: [{
                url: '/courses',
            }],
        events: [{
            title:"Indisponible",
            startTime: '15:00',
            endTime: '20:00',
            backgroundColor : "grey",
            borderColor : "black",
            textColor : "black",
            daysOfWeek: [ 6 ]
        }],
        dateClick: function(info) {
            window.open("/courseNew?start=" + info.dateStr.slice(0, -6).replace(":", "%3A"));
            alert('Clicked on: ' + info.dateStr);
            alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
            alert('Current view: ' + info.view.type);
        }
    });

    calendar.render();
});