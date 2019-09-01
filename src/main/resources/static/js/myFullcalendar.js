<script>
<!--
    $(document).ready(function() {
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2019-09-01', // TODO : en fonction de la date du jour
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            events: {
                url: '/allevents',
                type: 'GET',
                error: function() {
                    alert('there was an error while fetching events!');
                },
                //color: 'blue',   // a non-ajax option
                //textColor: 'white' // a non-ajax option
            }
        });

    });
-->
</script>