document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid', 'timeGrid', 'interaction'],
        defaultView: 'timeGridWeek',
        hiddenDays: [ 0 ],
        allDaySlot: false,
        locale: 'fr',
        minTime: "09:00:00",
        maxTime: "20:00:00",
        nowIndicator: true,
        height: "auto",
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        buttonText: {
            today: 'Aujourd\'hui',
            month: 'Mois',
            week : 'Semaine',
            day  : 'Jour',
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
           if (info.date > new Date().setHours(22, 0, 0, 0)){ if (info.view.type === "timeGridWeek" || info.view.type === "timeGridDay") {window.open("/newCourse?start=" + info.dateStr.slice(0, -6).replace(":", "%3A"),"_self");}}
           if (info.date < new Date().setHours(22, 0, 0, 0) && info.date > new Date()){ if (info.view.type === "timeGridWeek" || info.view.type === "timeGridDay") {alert("Si vous souhaitez creer un cours aujourd'hui merci de contacter le formateur au 06 ** ** ** **");}}
        }
    });

    calendar.render();
});