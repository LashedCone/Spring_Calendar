package spring.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.dto.CalendarDTO;
import spring.calendar.entity.Calendar;
import spring.calendar.entity.User;
import spring.calendar.repository.CalendarRepository;
import spring.calendar.repository.UserRepository;

import java.util.Optional;
@Service
public class CalendarService {
    @Autowired
    CalendarRepository calendarRepository;
    @Autowired
    UserRepository userRepository;
    
    public Calendar createCalendar(long userId, CalendarDTO calendarDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            Calendar calendar = new Calendar();
            calendar.setCalendarName(calendarDTO.getCalendarName());
            calendar.setDescription(calendarDTO.getDescription());
            calendar.setUser(user);
            return calendarRepository.save(calendar);
        }
        return null;
    }
    
    public Calendar searchCalendarById(long id) {
        return calendarRepository.findById(id).orElse(null);
    }
    
    public void deleteCalendar(long id) {
        calendarRepository.deleteById(id);
    }
    
//    public Calendar searchCalendarByName(String calendarName) {
//        return calendarRepository.findByCalendarName(calendarName);
//    }
    
    public Iterable<Calendar> getAllCalendar() {
        return calendarRepository.findAll();
    }
    
//    public Iterable<Calendar> searchCalendarByDescription(String description) {
//        return calendarRepository.findByDescription(description);
//    }
    
    public Calendar updateCalendar(long userId, long id, CalendarDTO updatedCalendar) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        Calendar existingCalendar = calendarRepository.findById(id).orElse(null);
        if(userOptional.isPresent()) {
            if(existingCalendar != null && existingCalendar.getUser().getId() == userId) {
                if(updatedCalendar.getCalendarName() != null) {
                    existingCalendar.setCalendarName(updatedCalendar.getCalendarName());
                }
                if(updatedCalendar.getDescription() != null) {
                    existingCalendar.setDescription(updatedCalendar.getDescription());
                }
                return calendarRepository.save(existingCalendar);
            }
        }
        return null;
    }
}
