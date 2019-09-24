document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid', 'timeGrid', 'interaction', 'list'],
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
            url: '/adminCourses',
        }],

        eventClick: function(info) {
            info.jsEvent.preventDefault();
            if (info.event.url !== "none") {
                window.open(info.event.url);
            }
        },

        events: [{
            title:"Indisponible",
            startTime: '15:00',
            endTime: '20:00',
            url: 'none',
            backgroundColor : "grey",
            borderColor : "black",
            textColor : "black",
            daysOfWeek: [ 6 ]
        }],
        dateClick: function(info) {
            if (info.date > new Date().setHours(22, 0, 0, 0)){ if (info.view.type === "timeGridWeek" || info.view.type === "timeGridDay") {window.open("/AdminNewCourse?start=" + info.dateStr.slice(0, -6).replace(":", "%3A"),"_self");}}
            if (info.date < new Date().setHours(22, 0, 0, 0) && info.date > new Date()){ if (info.view.type === "timeGridWeek" || info.view.type === "timeGridDay") {window.open("/AdminNewCourse?start=" + info.dateStr.slice(0, -6).replace(":", "%3A"),"_self");}}
        }
    });

    calendar.render();
});