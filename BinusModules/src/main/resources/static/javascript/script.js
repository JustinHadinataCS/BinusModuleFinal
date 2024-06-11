window.addEventListener('DOMContentLoaded', () => {
  const timeSlotContainer = document.querySelector('.time-slots');
  const courseBlockContainer = document.querySelector('.course-blocks');
  const startTime = '08:00';
  const endTime = '18:00';
  const timeIncrement = 15; // in minutes

  let currentTime = new Date(`2000-01-01T${startTime}`);
  const endDateTime = new Date(`2000-01-01T${endTime}`);

  // This will generate the time slots for the html.
  while (currentTime <= endDateTime) {
    const timeSlotElement = document.createElement('div');
    timeSlotElement.textContent = currentTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    timeSlotContainer.appendChild(timeSlotElement);

    currentTime = new Date(currentTime.getTime() + timeIncrement * 60000);
  }

  // This one is for the course block
  // You can just input the data, and the for loop will create a course block for each data.
  // Feeding these data to the function, and the function returns the value to the for loop, which uses it for its argument.
  const courses = [
    { name: 'Linear Algebra', startTime: '10:30', endTime: '12:10', day: 'MON', color: 'rgb(248, 242, 67)' },
    { name: 'Academic English', startTime: '1:30', endTime: '4:00', day: 'MON', color: 'rgb(151, 238, 151)' },
    { name: 'Data Structure Lab', startTime: '4:30', endTime: '6:10', day: 'MON', color: 'rgb(230, 144, 129)' },
    { name: 'Data Structure', startTime: '9:30', endTime: '11:10', day: 'TUE', color: 'rgb(88, 130, 154)' },
    { name: 'Data Structure', startTime: '11:30', endTime: '1:10', day: 'TUE', color: 'rgb(88, 130, 154)' },
    { name: 'Data Structure Lab', startTime: '3:30', endTime: '5:10', day: 'WED', color: 'rgb(86, 142, 91)' },
    { name: 'Object Oriented Programming', startTime: '9:30', endTime: '12:00', day: 'THU', color: 'rgb(231, 176, 104)' },
    { name: 'Project Hatchery', startTime: '1:30', endTime: '3:10', day: 'THU', color: 'rgb(240, 168, 232)' },
    { name: 'Calculus', startTime: '8:20', endTime: '9:40', day: 'FRI', color: 'rgb(239, 84, 84)' },
    { name: 'Calculus', startTime: '10:00', endTime: '11:40', day: 'FRI', color: 'rgb(239, 84, 84)' },
  ];

  // Take the argument from the calculate row and column and generate the course block based on those arguments.
  courses.forEach(course => {
    const startRow = calculateRowIndex(course.startTime, startTime, timeIncrement);
    const endRow = calculateRowIndex(course.endTime, startTime, timeIncrement) + 1;
    const columnIndex = calculateColumnIndex(course.day);

    const courseBlockElement = document.createElement('div');
    courseBlockElement.className = 'course-block';
    courseBlockElement.textContent = course.name;
    courseBlockElement.style.gridRow = `${startRow} / ${endRow}`;
    courseBlockElement.style.gridColumn = `${columnIndex} / ${columnIndex + 1}`;
    courseBlockElement.style.backgroundColor = course.color; // Set the background color
    courseBlockElement.id = course.name.replace(/\s+/g, '-').toLowerCase(); // Set the id attribute, using the course name

    courseBlockContainer.appendChild(courseBlockElement);
  });

  // Calculating the time so it can produce an argument for the for loop
  function calculateRowIndex(time, startTime, timeIncrement) {
    const [hours, minutes] = time.split(':').map(Number);
    const [startHours, startMinutes] = startTime.split(':').map(Number);
    const totalMinutes = (hours * 60 + minutes) - (startHours * 60 + startMinutes);
    return Math.floor(totalMinutes / timeIncrement);
  }

  // The same as above, but requires no calculation and serves as an argument for the course block table.
  function calculateColumnIndex(day) {
    const days = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'];
    return days.indexOf(day) + 1;
  }
});
