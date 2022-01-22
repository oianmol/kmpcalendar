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
    var jetMonth =  JetMonth.companion.current(date:  Kotlinx_datetimeLocalDate.init(year: 2022, month: Kotlinx_datetimeMonth.january, dayOfMonth: 1), firstDayOfWeek: Kotlinx_datetimeDayOfWeek.sunday)
    var body: some View {
      
        
        VStack {
            Text("\(jetMonth.startDate.dayOfMonth) \(jetMonth.startDate.month.name)")
            ForEach(jetMonth.monthWeeks,id: \.self) { week in
                HStack {
                    ForEach(week.days,id: \.self){ JetDay in
                        Text(String(JetDay.date.dayOfMonth))
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
