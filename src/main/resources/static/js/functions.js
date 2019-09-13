function cliAge(la_date)
{
    return Math.floor( (new Date).getTime() - la_date.getTime()) / (365.24*24*3600*1000);
}