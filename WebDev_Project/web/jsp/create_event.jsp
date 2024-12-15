<%-- 
    Document   : create_event
    Created on : Nov 29, 2024, 7:15:49 PM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Event - UA&P Directory</title>
        <script> 
            function updateRooms() { 
                var building = document.getElementById("building").value; 
                var floorSelect = document.getElementById("floor"); 
                var roomSelect = document.getElementById("room"); 
                var floors = { 
                    "ACB": ["Ground Floor", "2nd Floor", "3rd Floor", "4th Floor", "5th Floor"], 
                    "ALB": ["Ground Floor", "3rd Floor", "4th Floor"], 
                    "CAS": ["1st Floor", "2nd Floor", "3rd Floor", "4th Floor", "5th Floor"], 
                    "DCB": ["Ground Floor"], 
                    "PSB": ["4th Floor"], 
                    "USC": ["Ground Floor", "Mezzanine"] 
                }; 
                
                var rooms = { 
                    "ACB": { 
                        "Ground Floor": ["Promenade A", "Promenade B"],
                        "2nd Floor": ["201 A", "201 B", "202 A", "202 B", "203", "204 A", "204 B", "Li Seng Giap Auditorium"], 
                        "3rd Floor": ["301", "302", "303", "304 A", "304 B"], 
                        "4th Floor": ["401", "402", "403", "404 A", "404 B", "PLDT Hall"], 
                        "5th Floor": ["501 A", "501 B", "502", "503", "504", "505", "506", "507", "508", "Telengtan Hall"] 
                    }, 
                    "ALB": { 
                        "Ground Floor": ["Case Room 1", "Case Room 2", "Living Room", "Dining Hall 1", "Dining Hall 2", "Extension Room 1", "Extension Room 2"], 
                        "3rd Floor": ["Multimedia Hub"],
                        "4th Floor": ["Discussion Room A", "Discussion Room B"] 
                    }, 
                    "CAS": { 
                        "1st Floor": ["101", "102", "103", "104", "105", "106", "107", "108", "109"], 
                        "2nd Floor": ["202", "203"], 
                        "3rd Floor": ["301", "302", "303 A", "303 B", "Mac Lab"], 
                        "4th Floor": ["401", "404", "406", "MEL"],
                        "5th Floor": ["Work Room Red", "Work Room Green", "Work Room Big", "SSE Lib", "SSE Conference Room"]
                    }, 
                    "DCB": { "Ground Floor": ["Dizon Auditorium"] 
                    }, 
                    "PSB": { 
                        "4th Floor": ["SAR", "SBC"] 
                    }, 
                    "USC": { 
                        "Ground Floor": ["Open Space", "Open Room", "DR 00"],
                        "Mezzanine": ["DR 01", "DR 02", "DR 03", "DR 04", "DR 05", "DR 06", "DR 07", "DR 08", "DR 09", "DR 10"]
                    } 
                }; 
                
                floorSelect.innerHTML = ""; 
                roomSelect.innerHTML = ""; 
                
                floors[building].forEach(function(floor) { 
                    var option = document.createElement("option");
                    option.value = floor; option.text = floor; floorSelect.appendChild(option); 
                }); 
        
                floorSelect.onchange = function() { 
                    var selectedFloor = floorSelect.value; 
                    roomSelect.innerHTML = ""; 
                    rooms[building][selectedFloor].forEach(function(room) { 
                        var option = document.createElement("option"); 
                        option.value = room; 
                        option.text = room; roomSelect.appendChild(option); 
                        }); 
                    }; 
        
                floorSelect.dispatchEvent(new Event('change')); 
                } 
    
                window.onload = function() { updateRooms(); 
             };
                
            function calculateEndTime() { 
                const timeInput = document.getElementById("time").value;
         
                if (timeInput) { 
                    const [hours, minutes] = timeInput.split(":").map(Number); 
                    let endTime = new Date(); 
                    endTime.setHours(hours); 
                    endTime.setMinutes(minutes); 
                    
                    endTime.setMinutes(endTime.getMinutes() + 90); // Add 1 hour and 30 minutes 
                    
                    let endHours = endTime.getHours();
                    let endMinutes = endTime.getMinutes();
                    if (endMinutes >= 60) {
                        endHours += Math.floor(endMinutes / 60);
                        endMinutes = endMinutes % 60;
                    }
                
                    const endHoursStr = String(endHours).padStart(2, '0'); 
                    const endMinutesStr = String(endMinutes).padStart(2, '0');
                    const endTimeStr = `${endHoursStr}:${endMinutesStr}`; 
                
                    document.getElementById("endTime").innerText = "End Time: " + endTimeStr; 
                } 
            }
        </script>
    </head>
    <body>
        <h1><a href="home.jsp">UA&P Directory</a></h1>
        <h1>Create an Event</h1>
        <h2>Reservation Form</h2> 
        <form action="../do.create" method="post"> 
            <label for="title">Title:</label> 
            <input type="text" id="title" name="title" required><br><br>
            
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required><br><br>
            
            <label for="time">Time:</label>           
            <input type="time" id="time" name="time" required onchange="calculateEndTime()">
            <p id="endTime"></p>
            
            <label for="capacity">Capacity of People:</label>
            <input type="number" id="capacity" name="capacity" min="1" max="100" required><br><br>
            
            <label for="building">Building:</label> 
            <select id="building" name="building" onchange="updateRooms()" required>
                <option value="ACB">ACB</option>
                <option value="ALB">ALB</option> 
                <option value="CAS">CAS</option> 
                <option value="DCB">DCB</option> 
                <option value="PSB">PSB</option> 
                <option value="USC">USC</option> 
            </select><br><br>
            
            <label for="floor">Floor:</label>
            <select id="floor" name="floor" required> 
                <option value="Ground Floor">Ground Floor</option>
                <option value="2nd Floor">2nd Floor</option> 
                <option value="3rd Floor">3rd Floor</option> 
                <option value="4th Floor">4th Floor</option> 
                <option value="5th Floor">5th Floor</option>  <!-- Floor options will be populated based on the selected building -->
            </select><br><br>
            
            <label for="room">Room:</label>
            <select id="room" name="room" required> <!-- Room options will be populated based on the selected floor --> 
            </select><br><br>
            
            <label for="description">Description of the Event:</label><br> 
            <textarea type="text" id="description" name="description" rows="4" cols="50"></textarea><br><br>
            
            
            <input type="submit" value="Submit"> 
        </form>
    </body>
</html>
