//
//  CalendarView.swift
//  iosApp
//
//  Created by Anmol Verma on 22/01/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CalendarView: View {
    var jetYear =  JetYear.companion.current(date:  Kotlinx_datetimeLocalDate.init(year: 2022, month: Kotlinx_datetimeMonth.january, dayOfMonth: 1), firstDayOfWeek: Kotlinx_datetimeDayOfWeek.sunday)
    var body: some View {
      
        
        ScrollView{
            VStack{
                ForEach(jetYear.yearMonths,id: \.self){ jetMonth in
                    VStack {
                        Text("\(jetMonth.startDate.month.name)")
                        HStack {
                            ForEach(JetWeekKt.dayNames(dayOfWeek: Kotlinx_datetimeDayOfWeek.sunday),id: \.self){ name in
                                VStack{
                                    Text(name)
                                }.frame(width: 40, height: 40, alignment: Alignment.center).foregroundColor(Color.red)
                            }
                        }
                        ForEach(jetMonth.monthWeeks,id: \.self) { week in
                            HStack {
                                ForEach(week.days,id: \.self){ JetDay in
                                    VStack{
                                        Text(String(JetDay.date.dayOfMonth))
                                    }.frame(width: 40, height: 40, alignment: Alignment.center)
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        
      
    }
}

struct CalendarView_Previews: PreviewProvider {
    static var previews: some View {
        CalendarView()
    }
}
