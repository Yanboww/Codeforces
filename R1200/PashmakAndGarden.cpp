//459A
#include <iostream>
#include <cmath>

int main(){
    int x1, y1; std::cin >> x1 >> y1;
    int x2, y2; std::cin >> x2 >> y2;

    int dist;
    if(x1 == x2) dist = std::abs(y1-y2);
    else if(y1 == y2) dist = std::abs(x1-x2);
    else{
        int distx = std::abs(x1-x2);
        int disty = std::abs(y1-y2);
        if(distx == disty) dist = distx;
        else{
            std::cout << "-1\n";
            return 0;
        }
    }

    int x3 = -1001, y3 = -1001, x4 = -1001, y4 = -1001;
    if(x1 == x2){
        int cx1 = x1+dist;
        int cx2 = x1-dist;
        if(cx1 <= 1000){
            x3 = x4 = cx1;
            y3 = y1; y4 = y2;
        } else if(cx2 >= -1000){
            x3 = x4 = cx2;
            y3 = y1; y4 = y2;
        }
    } else if(y1 == y2){
        int cy1 = y1+dist;
        int cy2 = y1-dist;
        if(cy1 <= 1000){
            y3 = y4 = cy1;
            x3 = x1; x4 = x2;
        } else if(cy2 >= -1000){
            y3 = y4 = cy2;
            x3 = x1; x4 = x2;
        } 
    } else{
        x3 = x2; y3 = y1;
        x4 = x1; y4 = y2;
    }

    if(x3 == -1001) std::cout << "-1\n";
    else{
        std::cout << x3 << " " << y3 << " " << x4 << " " << y4 << "\n";
    }
}